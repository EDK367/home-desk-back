package com.solairy.homedesk.catalog.dto.response;

import java.time.OffsetDateTime;

public record ProductStockResponse(
        Long productId,
        String sku,
        String name,
        Integer stock,
        Integer minStockLevel,
        OffsetDateTime updatedAt
) {
}
