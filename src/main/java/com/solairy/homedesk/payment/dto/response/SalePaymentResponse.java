package com.solairy.homedesk.payment.dto.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record SalePaymentResponse(
        Long id,
        Long saleId,
        PaymentMethodResponse paymentMethod,
        BigDecimal amount,
        OffsetDateTime paymentDate,
        String referenceNumber,
        String notes,
        OffsetDateTime createdAt
) {
}
