package com.solairy.homedesk.auth.dto;

import com.solairy.homedesk.enums.RoleName;

import java.util.UUID;

public record UserSummaryResponse(
        UUID id,
        Integer employeeNumber,
        String username,
        String fullName,
        String email,
        RoleName role,
        Boolean isActive
) {
}
