package com.solairy.homedesk.sales.model;

import com.solairy.homedesk.auth.model.User;
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
@Table(name = "sales", schema = "public", indexes = {
        @Index(name = "idx_sales_customer_id",
                columnList = "customer_id"),
        @Index(name = "idx_sales_user_id",
                columnList = "user_id"),
        @Index(name = "idx_sales_status_id",
                columnList = "status_id"),
        @Index(name = "idx_sales_invoice_number",
                columnList = "invoice_number"),
        @Index(name = "idx_sales_sale_date",
                columnList = "sale_date")}, uniqueConstraints = {@UniqueConstraint(name = "sales_invoice_number_key",
        columnNames = {"invoice_number"})})
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "status_id", nullable = false)
    private SaleStatus status;

    @Size(max = 30)
    @NotNull
    @Column(name = "invoice_number", nullable = false, length = 30)
    private String invoiceNumber;

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "sale_date", nullable = false)
    private OffsetDateTime saleDate;

    @NotNull
    @Column(name = "subtotal", nullable = false, precision = 14, scale = 2)
    private BigDecimal subtotal;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "tax_amount", nullable = false, precision = 14, scale = 2)
    private BigDecimal taxAmount;

    @NotNull
    @Column(name = "total_amount", nullable = false, precision = 14, scale = 2)
    private BigDecimal totalAmount;

    @Size(max = 255)
    @Column(name = "invoice_pdf_path")
    private String invoicePdfPath;

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;


}