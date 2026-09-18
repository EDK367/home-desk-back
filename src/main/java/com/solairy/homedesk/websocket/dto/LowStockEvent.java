package com.solairy.homedesk.websocket.dto;

import java.math.BigDecimal;

public record LowStockEvent(
        Long productId,
        String sku,
        String productName,
        Integer stock,
        Integer minimumStock
) {
}
