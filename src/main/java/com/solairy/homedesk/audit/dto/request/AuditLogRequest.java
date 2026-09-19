package com.solairy.homedesk.audit.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuditLogRequest(
        @NotBlank(message = "La acción es requerida")
        @Size(max = 30)
        String action,

        @NotBlank(message = "El módulo es requerido")
        @Size(max = 50)
        String module,

        @Size(max = 50)
        String entityName,

        @Size(max = 50)
        String entityId,

        @Size(max = 255)
        String description
) {
}
