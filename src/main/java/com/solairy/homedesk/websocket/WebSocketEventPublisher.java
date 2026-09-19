package com.solairy.homedesk.websocket;

import com.solairy.homedesk.websocket.dto.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class WebSocketEventPublisher {

    private static final Logger log = LoggerFactory.getLogger(WebSocketEventPublisher.class);
    private static WebSocketEventPublisher instance;

    private final SimpMessagingTemplate messagingTemplate;

    @PostConstruct
    public void init() {
        instance = this;
    }

    public static WebSocketEventPublisher getInstance() {
        return instance;
    }

    public void stockUpdate(StockUpdateEvent event) {
        send("/topic/stock", event);
    }

    public void lowStock(LowStockEvent event) {
        send("/topic/low-stock", event);
        send("/topic/notifications", event);
    }

    public void lowStock(Long productId, String sku, String productName, Integer stock, Integer minimumStock) {
        lowStock(new LowStockEvent(productId, sku, productName, stock, minimumStock));
    }

    public void newPurchase(NewPurchaseEvent event) {
        send("/topic/purchases", event);
        send("/topic/new-purchase", event);
        send("/topic/notifications", event);
    }

    public void newPurchase(Long purchaseId, String supplierName, BigDecimal totalAmount) {
        newPurchase(new NewPurchaseEvent(purchaseId, supplierName, totalAmount));
    }

    public void newSale(NewSaleEvent event) {
        send("/topic/sales", event);
        send("/topic/new-sale", event);
        send("/topic/notifications", event);
    }

    public void newSale(Long saleId, String invoiceNumber, String customerName, BigDecimal totalAmount) {
        newSale(new NewSaleEvent(saleId, invoiceNumber, customerName, totalAmount));
    }

    public void newPayment(NewSalePaymentEvent event) {
        send("/topic/sale-payments", event);
        send("/topic/payments", event);
        send("/topic/new-payment", event);
        send("/topic/notifications", event);
    }

    public void newPayment(NewPaymentEvent event) {
        newPayment(new NewSalePaymentEvent(event.paymentId(), event.saleId(), event.paymentMethodName(), event.amount(), event.message()));
    }

    public void newPayment(Long paymentId, Long saleId, String paymentMethodName, BigDecimal amount) {
        newPayment(new NewSalePaymentEvent(paymentId, saleId, paymentMethodName, amount));
    }

    public void auditLog(AuditLogEvent event) {
        send("/topic/audit-logs", event);
        send("/topic/audit", event);
        send("/topic/notifications", event);
    }

    public void auditLog(Long auditLogId, String username, String action, String module, String entityName, String entityId, String description) {
        auditLog(new AuditLogEvent(auditLogId, username, action, module, entityName, entityId, description));
    }

    public void activityAudit(AuditLogEvent event) {
        auditLog(event);
    }

    public void activityAudit(Long auditLogId, String username, String action, String module, String entityName, String entityId, String description) {
        auditLog(auditLogId, username, action, module, entityName, entityId, description);
    }

    private void send(String destination, Object payload) {
        try {
            messagingTemplate.convertAndSend(destination, payload);
        } catch (Exception e) {
            log.error("Error sending WebSocket message to {}", destination, e);
        }
    }
}
