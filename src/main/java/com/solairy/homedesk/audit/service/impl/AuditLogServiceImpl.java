package com.solairy.homedesk.audit.service.impl;

import com.solairy.homedesk.audit.dto.request.AuditLogRequest;
import com.solairy.homedesk.audit.dto.response.AuditLogResponse;
import com.solairy.homedesk.audit.model.AuditLog;
import com.solairy.homedesk.audit.repository.AuditLogRepository;
import com.solairy.homedesk.audit.service.AuditLogService;
import com.solairy.homedesk.auth.model.User;
import com.solairy.homedesk.auth.repository.UserRepository;
import com.solairy.homedesk.common.exception.GlobalException;
import com.solairy.homedesk.common.response.PageResponse;
import com.solairy.homedesk.websocket.WebSocketEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl implements AuditLogService {

    private final AuditLogRepository auditLogRepository;
    private final UserRepository userRepository;
    private final WebSocketEventPublisher eventPublisher;

    @Override
    @Transactional(readOnly = true)
    public PageResponse<AuditLogResponse> findAll(Pageable pageable) {
        Page<AuditLog> page = auditLogRepository.findAll(pageable);
        return PageResponse.from(page, AuditLogResponse::from);
    }

    @Override
    @Transactional
    public AuditLogResponse log(AuditLogRequest request) {
        User user = getCurrentUser();
        AuditLog auditLog = new AuditLog();
        auditLog.setUser(user);
        auditLog.setAction(request.action());
        auditLog.setModule(request.module());
        auditLog.setEntityName(request.entityName());
        auditLog.setEntityId(request.entityId());
        auditLog.setDescription(request.description());
        auditLog.setCreatedAt(OffsetDateTime.now());

        AuditLog saved = auditLogRepository.save(auditLog);

        String username = user.getFullName() != null ? user.getFullName() : user.getUsername();
        eventPublisher.auditLog(saved.getId(), username, saved.getAction(), saved.getModule(), saved.getEntityName(), saved.getEntityId(), saved.getDescription());

        return AuditLogResponse.from(saved);
    }

    @Override
    @Transactional
    public void log(String action, String module, String entityName, String entityId, String description) {
        log(null, action, module, entityName, entityId, description);
    }

    @Override
    @Transactional
    public void log(User user, String action, String module, String entityName, String entityId, String description) {
        try {
            if (user == null) {
                user = getCurrentUserOrNull();
            }
            if (user == null) {
                return;
            }
            AuditLog auditLog = new AuditLog();
            auditLog.setUser(user);
            auditLog.setAction(action);
            auditLog.setModule(module);
            auditLog.setEntityName(entityName);
            auditLog.setEntityId(entityId);
            auditLog.setDescription(description);
            auditLog.setCreatedAt(OffsetDateTime.now());

            AuditLog saved = auditLogRepository.save(auditLog);

            String username = user.getFullName() != null ? user.getFullName() : user.getUsername();
            eventPublisher.auditLog(saved.getId(), username, saved.getAction(), saved.getModule(), saved.getEntityName(), saved.getEntityId(), saved.getDescription());
        } catch (Exception ignored) {
        }
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getName() == null) {
            throw new GlobalException(HttpStatus.UNAUTHORIZED, "Usuario no autenticado");
        }
        try {
            UUID userId = UUID.fromString(auth.getName());
            return userRepository.findById(userId)
                    .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        } catch (IllegalArgumentException e) {
            return userRepository.findByUsernameOrEmailIgnoreCase(auth.getName())
                    .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        }
    }

    private User getCurrentUserOrNull() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth == null || auth.getName() == null || "anonymousUser".equals(auth.getName())) {
                return null;
            }
            try {
                UUID userId = UUID.fromString(auth.getName());
                return userRepository.findById(userId).orElse(null);
            } catch (IllegalArgumentException e) {
                return userRepository.findByUsernameOrEmailIgnoreCase(auth.getName()).orElse(null);
            }
        } catch (Exception e) {
            return null;
        }
    }
}
