package com.solairy.homedesk.auth.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "El correo o usario es obligatorio")
        String usernameOrEmail,
        @NotBlank(message = "La contraseña es obligatoria")
        String password
) { }
