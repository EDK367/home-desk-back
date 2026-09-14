package com.solairy.homedesk.catalog.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ChangeMinStockRequest(

        @NotNull(message = "El nivel mínimo de stock es obligatorio")
        @Min(value = 0, message = "El nivel mínimo de stock no puede ser negativo")
        Integer minStockLevel
) {
}
