package com.solairy.homedesk.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Petición para cerrar sesión")
public record LogoutRequest(
        @Schema(description = "Refresh token de la sesión a revocar")
        String refreshToken
) {
}
