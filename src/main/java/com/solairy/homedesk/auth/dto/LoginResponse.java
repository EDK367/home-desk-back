package com.solairy.homedesk.auth.dto;

public record LoginResponse(
        String accessToken,
        String refreshToken,
        long expirationSeconds,
        long refreshExpirationSeconds,
        UserSummaryResponse user
) {
}
