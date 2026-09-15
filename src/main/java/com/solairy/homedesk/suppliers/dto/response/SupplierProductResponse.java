package com.solairy.homedesk.suppliers.dto.response;

import com.solairy.homedesk.catalog.dto.response.ProductResponse;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record SupplierProductResponse(
        Long id,
        SupplierResponse supplier,
        ProductResponse product,
        String supplierSku,
        BigDecimal cost,
        BigDecimal lastPurchaseCost,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
