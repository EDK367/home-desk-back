package com.solairy.homedesk.catalog.dto.response;

import com.solairy.homedesk.enums.UnitMeasureProduct;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ProductResponse(
        Long id,
        String sku,
        String name,
        String description,
        CategoryResponse category,
        UnitMeasureProduct unitOfMeasure,
        BigDecimal salePrice,
        Integer minStockLevel,
        Integer stock,
        Boolean isActive,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
