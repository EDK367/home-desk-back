package com.solairy.homedesk.inventory.model;

import com.solairy.homedesk.catalog.model.Product;
import com.solairy.homedesk.purchases.model.PurchaseItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "inventory_lots", schema = "public", indexes = {
        @Index(name = "idx_inventory_lots_product_received",
                columnList = "product_id, received_at"),
        @Index(name = "idx_inventory_lots_product_id",
                columnList = "product_id"),
        @Index(name = "idx_inventory_lots_purchase_item_id",
                columnList = "purchase_item_id")}, uniqueConstraints = {@UniqueConstraint(name = "inventory_lots_purchase_item_id_key",
        columnNames = {"purchase_item_id"})})
public class InventoryLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "purchase_item_id", nullable = false)
    private PurchaseItem purchaseItem;

    @NotNull
    @Column(name = "quantity_received", nullable = false)
    private Integer quantityReceived;

    @NotNull
    @Column(name = "quantity_remaining", nullable = false)
    private Integer quantityRemaining;

    @NotNull
    @Column(name = "unit_cost", nullable = false, precision = 12, scale = 2)
    private BigDecimal unitCost;

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "received_at", nullable = false)
    private OffsetDateTime receivedAt;

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;


}