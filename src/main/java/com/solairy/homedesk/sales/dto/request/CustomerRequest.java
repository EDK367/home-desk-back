package com.solairy.homedesk.sales.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerRequest(

        @NotBlank(message = "El nombre completo es obligatorio")
        @Size(max = 150, message = "El nombre completo no puede superar los 150 caracteres")
        String fullName,

        @Size(max = 30, message = "El número tributario no puede superar los 30 caracteres")
        String taxNumber,

        @Size(max = 30, message = "El teléfono no puede superar los 30 caracteres")
        String phone,

        @Email(message = "El correo electrónico no tiene un formato válido")
        @Size(max = 150, message = "El correo electrónico no puede superar los 150 caracteres")
        String email,

        @Size(max = 255, message = "La dirección no puede superar los 255 caracteres")
        String address
) {
}
