package com.solairy.homedesk.common.response;

import java.time.Instant;

public record ApiResponse<T>(
        int status,
        String message,
        T data,
        Instant timestamp
) {
    public static <T> ApiResponse<T> success(int status, String message, T data) {
        return new ApiResponse<>(status, message, data, Instant.now());
    }
}
