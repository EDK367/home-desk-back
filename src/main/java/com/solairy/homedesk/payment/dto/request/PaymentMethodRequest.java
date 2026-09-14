package com.solairy.homedesk.payment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PaymentMethodRequest(

        @NotBlank(message = "El nombre del método de pago es obligatorio")
        @Size(max = 50, message = "El nombre no puede superar los 50 caracteres")
        String name,

        @Size(max = 150, message = "La descripción no puede superar los 150 caracteres")
        String description
) {
}
