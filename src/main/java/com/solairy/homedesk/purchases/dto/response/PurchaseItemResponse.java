package com.solairy.homedesk.purchases.dto.response;

import com.solairy.homedesk.catalog.dto.response.ProductResponse;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record PurchaseItemResponse(
        Long id,
        ProductResponse product,
        Integer quantity,
        BigDecimal unitCost,
        BigDecimal subtotal,
        OffsetDateTime createdAt
) {
}
