package com.solairy.homedesk.audit.dto.response;

import com.solairy.homedesk.audit.model.AuditLog;

import java.time.OffsetDateTime;
import java.util.UUID;

public record AuditLogResponse(
        Long id,
        UUID userId,
        String username,
        String userFullName,
        String action,
        String module,
        String entityName,
        String entityId,
        String description,
        OffsetDateTime createdAt
) {
    public static AuditLogResponse from(AuditLog log) {
        if (log == null) {
            return null;
        }
        UUID userId = log.getUser() != null ? log.getUser().getId() : null;
        String username = log.getUser() != null ? log.getUser().getUsername() : null;
        String userFullName = log.getUser() != null ? log.getUser().getFullName() : null;
        return new AuditLogResponse(
                log.getId(),
                userId,
                username,
                userFullName,
                log.getAction(),
                log.getModule(),
                log.getEntityName(),
                log.getEntityId(),
                log.getDescription(),
                log.getCreatedAt()
        );
    }
}
