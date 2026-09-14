package com.solairy.homedesk.auth.controller;

import com.solairy.homedesk.auth.dto.request.LoginRequest;
import com.solairy.homedesk.auth.dto.response.LoginResponse;
import com.solairy.homedesk.auth.dto.request.LogoutRequest;
import com.solairy.homedesk.auth.dto.request.RefreshTokenRequest;
import com.solairy.homedesk.auth.dto.response.UserSummaryResponse;
import com.solairy.homedesk.auth.service.AuthCookieService;
import com.solairy.homedesk.auth.service.AuthService;
import com.solairy.homedesk.common.exception.GlobalException;
import com.solairy.homedesk.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user authentication and session management")
public class AuthController {

    private final AuthService authService;
    private final AuthCookieService authCookieService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        ResponseCookie cookie = authCookieService.createRefreshTokenCookie(response.refreshToken());

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(ApiResponse.success(
                        HttpStatus.OK.value(),
                        "Inicio de sesión exitoso",
                        response
                ));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<LoginResponse>> refreshToken(
            @RequestBody(required = false) RefreshTokenRequest request,
            HttpServletRequest httpRequest
    ) {
        String refreshToken = null;
        if (request != null && request.refreshToken() != null && !request.refreshToken().isBlank()) {
            refreshToken = request.refreshToken().trim();
        } else {
            refreshToken = authCookieService.extractRefreshToken(httpRequest);
        }

        if (refreshToken == null || refreshToken.isBlank()) {
            throw new GlobalException(HttpStatus.UNAUTHORIZED, "Refresh token no proporcionado (ni en cookie ni en cuerpo de la petición)");
        }

        LoginResponse response = authService.refreshToken(refreshToken);
        ResponseCookie cookie = authCookieService.createRefreshTokenCookie(response.refreshToken());

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(ApiResponse.success(
                        HttpStatus.OK.value(),
                        "Token renovado exitosamente",
                        response
                ));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout(
            @RequestBody(required = false) LogoutRequest request,
            HttpServletRequest httpRequest,
            Authentication authentication
    ) {
        String refreshToken = null;
        if (request != null && request.refreshToken() != null && !request.refreshToken().isBlank()) {
            refreshToken = request.refreshToken().trim();
        } else {
            refreshToken = authCookieService.extractRefreshToken(httpRequest);
        }

        UUID userId = null;
        if (authentication != null && authentication.getName() != null) {
            try {
                userId = UUID.fromString(authentication.getName());
            } catch (IllegalArgumentException ignored) {
            }
        }

        authService.logout(refreshToken, userId);

        ResponseEntity.BodyBuilder responseBuilder = ResponseEntity.ok();
        for (ResponseCookie cleanCookie : authCookieService.createAllCleanCookies()) {
            responseBuilder.header(HttpHeaders.SET_COOKIE, cleanCookie.toString());
        }

        return responseBuilder.body(ApiResponse.success(
                HttpStatus.OK.value(),
                "Sesión cerrada exitosamente",
                "La sesión ha sido revocada correctamente."
        ));
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserSummaryResponse>> me(Authentication authentication) {
        if (authentication == null || authentication.getName() == null) {
            throw new GlobalException(HttpStatus.UNAUTHORIZED, "No autenticado: token no proporcionado o inválido");
        }

        UUID userId;
        try {
            userId = UUID.fromString(authentication.getName());
        } catch (IllegalArgumentException e) {
            throw new GlobalException(HttpStatus.UNAUTHORIZED, "Token de autenticación inválido");
        }

        UserSummaryResponse response = authService.getCurrentUser(userId);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Información del usuario obtenida exitosamente",
                response
        ));
    }
}
