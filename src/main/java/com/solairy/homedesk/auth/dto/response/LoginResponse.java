package com.solairy.homedesk.auth.dto.response;

public record LoginResponse(
        String accessToken,
        String refreshToken,
        long expirationSeconds,
        long refreshExpirationSeconds,
        UserSummaryResponse user
) {
}
