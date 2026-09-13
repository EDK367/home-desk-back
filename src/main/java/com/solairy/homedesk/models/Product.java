package com.solairy.homedesk.models;

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
@Table(name = "products", schema = "public", indexes = {
        @Index(name = "idx_products_sku",
                columnList = "sku"),
        @Index(name = "idx_products_name",
                columnList = "name"),
        @Index(name = "idx_products_category_id",
                columnList = "category_id")}, uniqueConstraints = {@UniqueConstraint(name = "products_sku_key",
        columnNames = {"sku"})})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "sku", nullable = false, length = 50)
    private String sku;

    @Size(max = 150)
    @NotNull
    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Size(max = 20)
    @NotNull
    @ColumnDefault("'UNIT'")
    @Column(name = "unit_of_measure", nullable = false, length = 20)
    private String unitOfMeasure;

    @NotNull
    @Column(name = "sale_price", nullable = false, precision = 12, scale = 2)
    private BigDecimal salePrice;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "min_stock_level", nullable = false)
    private Integer minStockLevel;

    @NotNull
    @ColumnDefault("true")
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;


}