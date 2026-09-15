package com.solairy.homedesk.purchases.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;
import java.util.List;

public record PurchaseRequest(
        @NotNull(message = "El ID del proveedor es obligatorio")
        Long supplierId,

        Long statusId,

        OffsetDateTime purchaseDate,

        @Size(max = 255, message = "Las notas no pueden superar los 255 caracteres")
        String notes,

        @NotEmpty(message = "La compra debe tener al menos un ítem")
        @Valid
        List<PurchaseItemRequest> items
) {
}
