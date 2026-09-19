package com.solairy.homedesk.auth.service.impl;

import com.solairy.homedesk.auth.dto.request.LoginRequest;
import com.solairy.homedesk.auth.dto.response.LoginResponse;
import com.solairy.homedesk.auth.dto.response.UserSummaryResponse;
import com.solairy.homedesk.auth.mapper.AuthMapper;
import com.solairy.homedesk.auth.model.Session;
import com.solairy.homedesk.auth.model.User;
import com.solairy.homedesk.auth.repository.SessionRepository;
import com.solairy.homedesk.auth.repository.UserRepository;
import com.solairy.homedesk.auth.service.AuthService;
import com.solairy.homedesk.auth.service.JwtService;
import com.solairy.homedesk.common.exception.GlobalException;
import com.solairy.homedesk.audit.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.OffsetDateTime;
import java.util.HexFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthMapper authMapper;
    private final AuditLogService auditLogService;

    @Override
    @Transactional
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsernameOrEmailIgnoreCase(request.usernameOrEmail())
                .orElseThrow(() -> new GlobalException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new GlobalException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
        }

        if (!Boolean.TRUE.equals(user.getIsActive())) {
            throw new GlobalException(HttpStatus.FORBIDDEN, "La cuenta está desactivada. Por favor, contacte al administrador.");
        }

        String roleName = user.getRole().getName().name();
        List<String> roles = List.of(roleName);

        String accessToken = jwtService.generateToken(user, roles);
        String refreshToken = jwtService.generateRefreshToken(user);

        OffsetDateTime now = OffsetDateTime.now();
        Session session = new Session();
        session.setUser(user);
        session.setTokenHash(hashToken(refreshToken));
        session.setCreatedAt(now);
        session.setExpiresAt(now.plusSeconds(jwtService.getRefreshExpirationSeconds()));
        session.setRevokedAt(null);
        sessionRepository.save(session);

        auditLogService.log(user, "LOGIN", "AUTH", "User", user.getId().toString(), "Inicio de sesión exitoso");

        UserSummaryResponse userSummary = authMapper.toUserSummaryResponse(user);

        return new LoginResponse(
                accessToken,
                refreshToken,
                jwtService.getExpirationSeconds(),
                jwtService.getRefreshExpirationSeconds(),
                userSummary
        );
    }

    @Override
    @Transactional
    public LoginResponse refreshToken(String refreshToken) {
        if (refreshToken == null || refreshToken.isBlank()) {
            throw new GlobalException(HttpStatus.UNAUTHORIZED, "Refresh token no proporcionado");
        }

        if (!jwtService.isRefreshTokenValid(refreshToken)) {
            throw new GlobalException(HttpStatus.UNAUTHORIZED, "Refresh token inválido o expirado");
        }

        String hashedToken = hashToken(refreshToken.trim());
        Session session = sessionRepository.findByTokenHash(hashedToken)
                .orElseThrow(() -> new GlobalException(HttpStatus.UNAUTHORIZED, "Sesión no encontrada o token inválido"));

        if (session.getRevokedAt() != null) {
            throw new GlobalException(HttpStatus.UNAUTHORIZED, "La sesión ha sido revocada");
        }

        if (session.getExpiresAt().isBefore(OffsetDateTime.now())) {
            throw new GlobalException(HttpStatus.UNAUTHORIZED, "La sesión ha expirado");
        }

        User user = session.getUser();
        if (!Boolean.TRUE.equals(user.getIsActive())) {
            throw new GlobalException(HttpStatus.FORBIDDEN, "La cuenta está desactivada");
        }

        String roleName = user.getRole().getName().name();
        List<String> roles = List.of(roleName);

        String newAccessToken = jwtService.generateToken(user, roles);
        String newRefreshToken = jwtService.generateRefreshToken(user);

        OffsetDateTime now = OffsetDateTime.now();
        session.setTokenHash(hashToken(newRefreshToken));
        session.setExpiresAt(now.plusSeconds(jwtService.getRefreshExpirationSeconds()));
        sessionRepository.save(session);

        UserSummaryResponse userSummary = authMapper.toUserSummaryResponse(user);

        return new LoginResponse(
                newAccessToken,
                newRefreshToken,
                jwtService.getExpirationSeconds(),
                jwtService.getRefreshExpirationSeconds(),
                userSummary
        );
    }

    @Override
    @Transactional
    public void logout(String refreshToken, UUID authenticatedUserId) {
        OffsetDateTime now = OffsetDateTime.now();

        if (refreshToken != null && !refreshToken.isBlank()) {
            String hashedToken = hashToken(refreshToken.trim());
            Optional<Session> sessionOpt = sessionRepository.findByTokenHash(hashedToken);
            if (sessionOpt.isPresent()) {
                Session session = sessionOpt.get();
                if (session.getRevokedAt() == null) {
                    session.setRevokedAt(now);
                    sessionRepository.save(session);
                    auditLogService.log(session.getUser(), "LOGOUT", "AUTH", "User", session.getUser().getId().toString(), "Cierre de sesión");
                }
                return;
            }
        }

        if (authenticatedUserId != null) {
            List<Session> activeSessions = sessionRepository.findByUserIdAndRevokedAtIsNull(authenticatedUserId);
            for (Session s : activeSessions) {
                s.setRevokedAt(now);
            }
            if (!activeSessions.isEmpty()) {
                sessionRepository.saveAll(activeSessions);
                User user = activeSessions.get(0).getUser();
                auditLogService.log(user, "LOGOUT", "AUTH", "User", authenticatedUserId.toString(), "Cierre de sesión");
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserSummaryResponse getCurrentUser(UUID userId) {
        User user = userRepository.findByIdWithRole(userId)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        return authMapper.toUserSummaryResponse(user);
    }

    private String hashToken(String token) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(token.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al generar hash de seguridad", e);
        }
    }
}
