package com.solairy.homedesk.purchases.dto.response;

import java.time.OffsetDateTime;

public record PurchaseStatusResponse(
        Long id,
        String name,
        String description,
        Boolean isActive,
        OffsetDateTime createdAt
) {
}
