package com.solairy.homedesk.auth.dto.response;

import com.solairy.homedesk.enums.RoleName;

public record RoleResponse(
        Long id,
        RoleName name
) {
}
