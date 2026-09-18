package com.solairy.homedesk.websocket.dto;

public record StockUpdateEvent(
        Long productId,
        String sku,
        String productName,
        Integer previousStock,
        Integer newStock,
        String movementType
) {
}
