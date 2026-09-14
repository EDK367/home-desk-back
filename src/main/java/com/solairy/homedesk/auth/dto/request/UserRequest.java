package com.solairy.homedesk.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequest(

        @NotBlank(message = "El nombre completo es obligatorio")
        @Size(max = 150, message = "El nombre completo no puede superar los 150 caracteres")
        String fullName,

        // esto si hay tiempo en un futuro se cambiara
        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 6, max = 100, message = "La contraseña no puede ser menor a 6 o mayor a 100 caracteres")
        String password,

        @NotBlank(message = "El correo electrónico es obligatorio")
        @Email(message = "El correo electrónico no es válido")
        @Size(max = 150, message = "El correo no puede superar los 150 caracteres")
        String email,

        @NotNull(message = "El rol es obligatorio")
        Long roleId

) {
}