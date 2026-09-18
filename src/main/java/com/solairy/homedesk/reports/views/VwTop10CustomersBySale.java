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
@Table(name = "vw_top_10_customers_by_sales", schema = "public")
public class VwTop10CustomersBySale {

    @Id
    @Column(name = "id")
    private Long id;

    @Size(max = 150)
    @Column(name = "customer_name", length = 150)
    private String customerName;

    @Column(name = "sale_count")
    private Long saleCount;

    @Column(name = "total_purchase_amount")
    private BigDecimal totalPurchaseAmount;

    @Column(name = "last_purchase_date")
    private OffsetDateTime lastPurchaseDate;


}