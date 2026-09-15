package com.solairy.homedesk.sales.dto.response;

import com.solairy.homedesk.catalog.dto.response.ProductResponse;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record SaleItemResponse(
        Long id,
        ProductResponse product,
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal subtotal,
        OffsetDateTime createdAt
) {
}
