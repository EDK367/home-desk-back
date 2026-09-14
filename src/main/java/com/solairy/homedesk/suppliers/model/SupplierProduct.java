package com.solairy.homedesk.suppliers.model;

import com.solairy.homedesk.catalog.model.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "supplier_products", schema = "public", indexes = {
        @Index(name = "idx_supplier_products_supplier_id",
                columnList = "supplier_id"),
        @Index(name = "idx_supplier_products_product_id",
                columnList = "product_id")}, uniqueConstraints = {@UniqueConstraint(name = "uq_supplier_products",
        columnNames = {
                "supplier_id",
                "product_id"})})
public class SupplierProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Size(max = 30)
    @Column(name = "supplier_sku", length = 30)
    private String supplierSku;

    @Column(name = "cost", precision = 12, scale = 2)
    private BigDecimal cost;

    @Column(name = "last_purchase_cost", precision = 12, scale = 2)
    private BigDecimal lastPurchaseCost;

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;


}