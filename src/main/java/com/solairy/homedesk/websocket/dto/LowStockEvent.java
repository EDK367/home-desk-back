package com.solairy.homedesk.websocket.dto;

public record LowStockEvent(
        Long productId,
        String sku,
        String productName,
        Integer stock,
        Integer minimumStock,
        String message
) {
    public LowStockEvent(Long productId, String sku, String productName, Integer stock, Integer minimumStock) {
        this(productId, sku, productName, stock, minimumStock, productName != null ? productName + " tiene stock bajo" : "Producto tiene stock bajo");
    }
}
