package com.solairy.homedesk.inventory.dto.response;

import com.solairy.homedesk.catalog.dto.response.ProductResponse;

import java.time.OffsetDateTime;

public record InventoryResponse(
        Long id,
        ProductResponse product,
        Integer quantityOnHand,
        Boolean isLowStock,
        OffsetDateTime updatedAt
) {
}
