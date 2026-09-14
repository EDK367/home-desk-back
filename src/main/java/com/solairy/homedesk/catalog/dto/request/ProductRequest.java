package com.solairy.homedesk.catalog.dto.request;

import com.solairy.homedesk.enums.UnitMeasureProduct;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductRequest(

        @NotBlank(message = "El SKU es obligatorio")
        @Size(max = 50, message = "El SKU no puede superar los 50 caracteres")
        String sku,

        @NotBlank(message = "El nombre del producto es obligatorio")
        @Size(max = 150, message = "El nombre del producto no puede superar los 150 caracteres")
        String name,

        String description,

        @NotNull(message = "La categoría es obligatoria")
        Long categoryId,

        @NotNull(message = "La unidad de medida es obligatoria")
        UnitMeasureProduct unitOfMeasure,

        @NotNull(message = "El precio de venta es obligatorio")
        @DecimalMin(value = "0.0", message = "El precio de venta no puede ser negativo")
        BigDecimal salePrice,

        @NotNull(message = "El nivel mínimo de stock es obligatorio")
        @Min(value = 0, message = "El nivel mínimo de stock no puede ser negativo")
        Integer minStockLevel
) {
}
