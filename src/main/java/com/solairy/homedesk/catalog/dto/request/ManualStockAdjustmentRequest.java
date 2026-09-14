package com.solairy.homedesk.catalog.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ManualStockAdjustmentRequest(

        @NotNull(message = "La cantidad del ajuste es obligatoria")
        Integer quantity,

        @NotBlank(message = "El motivo del ajuste es obligatorio")
        @Size(max = 255, message = "El motivo no puede superar los 255 caracteres")
        String notes
) {
}
