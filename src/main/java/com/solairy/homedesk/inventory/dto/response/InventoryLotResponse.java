package com.solairy.homedesk.inventory.dto.response;

import com.solairy.homedesk.catalog.dto.response.ProductResponse;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record InventoryLotResponse(
        Long id,
        ProductResponse product,
        Long purchaseItemId,
        Long purchaseId,
        Integer quantityReceived,
        Integer quantityRemaining,
        BigDecimal unitCost,
        OffsetDateTime receivedAt,
        OffsetDateTime createdAt
) {
}
