package com.solairy.homedesk.payment.dto.response;

import java.time.OffsetDateTime;

public record PaymentMethodResponse(
        Long id,
        String name,
        String description,
        Boolean isActive,
        OffsetDateTime createdAt
) {
}
