package com.solairy.homedesk.websocket.dto;

public record AuditLogEvent(
        Long auditLogId,
        String username,
        String action,
        String module,
        String entityName,
        String entityId,
        String description,
        String message
) {
    public AuditLogEvent(Long auditLogId, String username, String action, String module, String entityName, String entityId, String description) {
        this(auditLogId, username, action, module, entityName, entityId, description, formatMessage(username, description, action));
    }

    private static String formatMessage(String username, String description, String action) {
        String user = (username != null && !username.isBlank()) ? username : "Usuario";
        if (description != null && !description.isBlank()) {
            if (description.startsWith(user)) {
                return description;
            }
            return user + " " + description;
        }
        return user + " realizó acción: " + (action != null ? action : "desconocida");
    }
}
