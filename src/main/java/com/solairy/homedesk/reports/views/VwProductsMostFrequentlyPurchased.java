package com.solairy.homedesk.reports.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;

@Getter
@Entity
@Immutable
@Table(name = "vw_products_most_frequently_purchased", schema = "public")
public class VwProductsMostFrequentlyPurchased {

    @Id
    @Column(name = "product_id")
    private Long productId;

    @Size(max = 50)
    @Column(name = "sku", length = 50)
    private String sku;

    @Size(max = 150)
    @Column(name = "product", length = 150)
    private String product;

    @Size(max = 100)
    @Column(name = "category", length = 100)
    private String category;

    @Column(name = "purchase_count")
    private Long purchaseCount;

    @Column(name = "total_quantity_purchased")
    private Long totalQuantityPurchased;

    @Column(name = "total_purchase_amount")
    private BigDecimal totalPurchaseAmount;


}