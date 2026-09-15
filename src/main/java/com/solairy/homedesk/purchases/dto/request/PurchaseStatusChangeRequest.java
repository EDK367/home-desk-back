package com.solairy.homedesk.purchases.dto.request;

import jakarta.validation.constraints.NotNull;

public record PurchaseStatusChangeRequest(
        @NotNull(message = "El ID del estado es obligatorio")
        Long statusId
) {
}
