package com.solairy.homedesk.websocket.dto;

import java.math.BigDecimal;

public record NewSaleEvent(
        Long saleId,
        String invoiceNumber,
        String customerName,
        BigDecimal totalAmount,
        String message
) {
    public NewSaleEvent(Long saleId, String invoiceNumber, String customerName, BigDecimal totalAmount) {
        this(saleId, invoiceNumber, customerName, totalAmount, "Se registró la venta " + (invoiceNumber != null ? invoiceNumber : ""));
    }
}
