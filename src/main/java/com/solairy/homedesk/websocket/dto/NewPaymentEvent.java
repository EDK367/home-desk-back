package com.solairy.homedesk.websocket.dto;

import java.math.BigDecimal;

public record NewPaymentEvent(
        Long paymentId,
        Long saleId,
        String paymentMethodName,
        BigDecimal amount,
        String message
) {
    public NewPaymentEvent(Long paymentId, Long saleId, String paymentMethodName, BigDecimal amount) {
        this(paymentId, saleId, paymentMethodName, amount, "Se recibió un pago de Q" + formatAmount(amount));
    }

    private static String formatAmount(BigDecimal amount) {
        if (amount == null) {
            return "0";
        }
        BigDecimal stripped = amount.stripTrailingZeros();
        return (stripped.scale() <= 0) ? stripped.setScale(0).toPlainString() : stripped.toPlainString();
    }
}
