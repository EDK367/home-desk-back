package com.solairy.homedesk.websocket;

import com.solairy.homedesk.common.response.ApiResponse;
import com.solairy.homedesk.websocket.dto.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/socket")
@RequiredArgsConstructor
@Tag(name = "WebSocket", description = "Endpoints para notificación y prueba de WebSocket")
public class WebSocketController {

    private final WebSocketEventPublisher eventPublisher;

    @PostMapping("/low-stock")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'INVENTORY')")
    public ResponseEntity<ApiResponse<LowStockEvent>> publishLowStock(@RequestBody LowStockEvent event) {
        eventPublisher.lowStock(event);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Evento de stock bajo publicado exitosamente",
                event
        ));
    }

    @PostMapping("/new-purchase")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'PURCHASING')")
    public ResponseEntity<ApiResponse<NewPurchaseEvent>> publishNewPurchase(@RequestBody NewPurchaseEvent event) {
        eventPublisher.newPurchase(event);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Evento de nueva compra publicado exitosamente",
                event
        ));
    }

    @PostMapping("/new-sale")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'SALES')")
    public ResponseEntity<ApiResponse<NewSaleEvent>> publishNewSale(@RequestBody NewSaleEvent event) {
        eventPublisher.newSale(event);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Evento de nueva venta publicado exitosamente",
                event
        ));
    }

    @PostMapping("/new-payment")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'SALES')")
    public ResponseEntity<ApiResponse<NewSalePaymentEvent>> publishNewPayment(@RequestBody NewSalePaymentEvent event) {
        eventPublisher.newPayment(event);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Evento de nuevo pago publicado exitosamente",
                event
        ));
    }

    @PostMapping("/audit-log")
    @PreAuthorize("hasRole('ADMINISTRATION')")
    public ResponseEntity<ApiResponse<AuditLogEvent>> publishAuditLog(@RequestBody AuditLogEvent event) {
        eventPublisher.auditLog(event);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Evento de auditoría publicado exitosamente",
                event
        ));
    }

    @MessageMapping("/low-stock")
    public void handleLowStockMessage(LowStockEvent event) {
        eventPublisher.lowStock(event);
    }

    @MessageMapping("/new-purchase")
    public void handleNewPurchaseMessage(NewPurchaseEvent event) {
        eventPublisher.newPurchase(event);
    }

    @MessageMapping("/new-sale")
    public void handleNewSaleMessage(NewSaleEvent event) {
        eventPublisher.newSale(event);
    }

    @MessageMapping("/new-payment")
    public void handleNewPaymentMessage(NewSalePaymentEvent event) {
        eventPublisher.newPayment(event);
    }

    @MessageMapping("/audit-log")
    public void handleAuditLogMessage(AuditLogEvent event) {
        eventPublisher.auditLog(event);
    }
}
