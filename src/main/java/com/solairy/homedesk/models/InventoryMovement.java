package com.solairy.homedesk.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "inventory_movements", schema = "public", indexes = {
        @Index(name = "idx_inventory_movements_product_id",
                columnList = "product_id"),
        @Index(name = "idx_inventory_movements_user_id",
                columnList = "user_id"),
        @Index(name = "idx_inventory_movements_reference",
                columnList = "reference_type, reference_id"),
        @Index(name = "idx_inventory_movements_created_at",
                columnList = "created_at")})
public class InventoryMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Size(max = 30)
    @NotNull
    @Column(name = "movement_type", nullable = false, length = 30)
    private String movementType;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @NotNull
    @Column(name = "balance_after", nullable = false)
    private Integer balanceAfter;

    @Size(max = 30)
    @Column(name = "reference_type", length = 30)
    private String referenceType;

    @Column(name = "reference_id")
    private Long referenceId;

    @Size(max = 10)
    @Column(name = "costing_method", length = 10)
    private String costingMethod;

    @Size(max = 255)
    @Column(name = "notes")
    private String notes;

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;


}