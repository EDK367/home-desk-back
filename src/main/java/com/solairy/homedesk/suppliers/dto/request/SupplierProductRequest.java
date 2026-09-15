package com.solairy.homedesk.suppliers.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record SupplierProductRequest(
        @NotNull(message = "El ID del proveedor es obligatorio")
        Long supplierId,

        @NotNull(message = "El ID del producto es obligatorio")
        Long productId,

        @Size(max = 30, message = "El SKU del proveedor no puede exceder 30 caracteres")
        String supplierSku,

        @DecimalMin(value = "0.0", inclusive = true, message = "El costo no puede ser negativo")
        BigDecimal cost
) {
}
