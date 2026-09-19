package com.solairy.homedesk.websocket.dto;

import java.math.BigDecimal;

public record NewPurchaseEvent(
        Long purchaseId,
        String supplierName,
        BigDecimal totalAmount,
        String message
) {
    public NewPurchaseEvent(Long purchaseId, String supplierName, BigDecimal totalAmount) {
        this(purchaseId, supplierName, totalAmount, "Se registró una nueva compra #" + (purchaseId != null ? purchaseId : ""));
    }
}
