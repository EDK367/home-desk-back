package com.solairy.homedesk.sales.dto.response;

import java.time.OffsetDateTime;

public record CustomerResponse(
        Long id,
        String fullName,
        String taxNumber,
        String phone,
        String email,
        String address,
        Boolean isActive,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
