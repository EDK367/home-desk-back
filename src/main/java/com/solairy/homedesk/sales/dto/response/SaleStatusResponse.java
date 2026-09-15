package com.solairy.homedesk.sales.dto.response;

import java.time.OffsetDateTime;

public record SaleStatusResponse(
        Long id,
        String name,
        String description,
        Boolean isActive,
        OffsetDateTime createdAt
) {
}
