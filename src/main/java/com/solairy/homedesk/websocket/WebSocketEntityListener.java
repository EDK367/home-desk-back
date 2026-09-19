package com.solairy.homedesk.websocket;

import com.solairy.homedesk.audit.model.AuditLog;
import com.solairy.homedesk.inventory.model.Inventory;
import com.solairy.homedesk.payment.model.SalePayment;
import com.solairy.homedesk.purchases.model.Purchase;
import com.solairy.homedesk.sales.model.Sale;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import org.springframework.stereotype.Component;

@Component
public class WebSocketEntityListener {

    @PostPersist
    public void onPostPersist(Object entity) {
        WebSocketEventPublisher publisher = WebSocketEventPublisher.getInstance();
        if (publisher == null) {
            return;
        }

        if (entity instanceof Purchase purchase) {
            handlePurchase(publisher, purchase);
        } else if (entity instanceof Sale sale) {
            handleSale(publisher, sale);
        } else if (entity instanceof SalePayment payment) {
            handlePayment(publisher, payment);
        } else if (entity instanceof AuditLog log) {
            handleAuditLog(publisher, log);
        } else if (entity instanceof Inventory inventory) {
            handleInventory(publisher, inventory);
        }
    }

    @PostUpdate
    public void onPostUpdate(Object entity) {
        WebSocketEventPublisher publisher = WebSocketEventPublisher.getInstance();
        if (publisher == null) {
            return;
        }

        if (entity instanceof Inventory inventory) {
            handleInventory(publisher, inventory);
        }
    }

    private void handlePurchase(WebSocketEventPublisher publisher, Purchase purchase) {
        String supplierName = purchase.getSupplier() != null ? purchase.getSupplier().getName() : null;
        publisher.newPurchase(purchase.getId(), supplierName, purchase.getTotalAmount());
    }

    private void handleSale(WebSocketEventPublisher publisher, Sale sale) {
        String customerName = sale.getCustomer() != null ? sale.getCustomer().getFullName() : null;
        publisher.newSale(sale.getId(), sale.getInvoiceNumber(), customerName, sale.getTotalAmount());
    }

    private void handlePayment(WebSocketEventPublisher publisher, SalePayment payment) {
        Long saleId = payment.getSale() != null ? payment.getSale().getId() : null;
        String methodName = payment.getPaymentMethod() != null ? payment.getPaymentMethod().getName() : null;
        publisher.newPayment(payment.getId(), saleId, methodName, payment.getAmount());
    }

    private void handleAuditLog(WebSocketEventPublisher publisher, AuditLog log) {
        String username = null;
        if (log.getUser() != null) {
            username = log.getUser().getFullName() != null ? log.getUser().getFullName() : log.getUser().getUsername();
        }
        publisher.auditLog(log.getId(), username, log.getAction(), log.getModule(), log.getEntityName(), log.getEntityId(), log.getDescription());
    }

    private void handleInventory(WebSocketEventPublisher publisher, Inventory inventory) {
        if (inventory.getProduct() != null && inventory.getQuantityOnHand() != null) {
            Integer minStock = inventory.getProduct().getMinStockLevel();
            if (minStock != null && inventory.getQuantityOnHand() <= minStock) {
                publisher.lowStock(
                        inventory.getProduct().getId(),
                        inventory.getProduct().getSku(),
                        inventory.getProduct().getName(),
                        inventory.getQuantityOnHand(),
                        minStock
                );
            }
        }
    }
}
