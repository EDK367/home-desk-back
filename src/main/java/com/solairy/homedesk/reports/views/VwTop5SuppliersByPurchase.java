package com.solairy.homedesk.reports.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Entity
@Immutable
@Table(name = "vw_top_5_suppliers_by_purchases", schema = "public")
public class VwTop5SuppliersByPurchase {

    @Id
    @Column(name = "supplier_id")
    private Long supplierId;

    @Size(max = 150)
    @Column(name = "supplier_name", length = 150)
    private String supplierName;

    @Column(name = "purchase_count")
    private Long purchaseCount;

    @Column(name = "total_purchase_amount")
    private BigDecimal totalPurchaseAmount;

    @Column(name = "last_purchase_date")
    private OffsetDateTime lastPurchaseDate;


}