package com.solairy.homedesk.sales.dto.request;

import jakarta.validation.constraints.NotNull;

public record SaleStatusChangeRequest(
        @NotNull(message = "El ID del estado es obligatorio")
        Long statusId
) {
}
