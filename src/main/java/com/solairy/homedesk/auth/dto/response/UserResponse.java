package com.solairy.homedesk.auth.dto.response;

import java.time.OffsetDateTime;
import java.util.UUID;

public record UserResponse(
        UUID id,
        Integer employeeNumber,
        String username,
        String fullName,
        String email,
        RoleResponse role,
        Boolean isActive,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
