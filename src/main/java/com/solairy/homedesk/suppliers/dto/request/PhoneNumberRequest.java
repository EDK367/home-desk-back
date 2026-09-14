package com.solairy.homedesk.suppliers.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PhoneNumberRequest(

        @NotBlank(message = "Se necesita un número de teléfono para el contacto")
        @Pattern(
                regexp = "^\\+\\d+ \\d+$",
                message = "El teléfono debe tener el formato +(CODIGO) NUMERO"
        )
        String phone
) {
}
