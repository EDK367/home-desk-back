package com.solairy.homedesk.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "phone_suppliers", schema = "public", indexes = {
        @Index(name = "idx_phone_suppliers_supplier_id",
                columnList = "supplier_id"),
        @Index(name = "idx_phone_suppliers_phone_id",
                columnList = "phone_id")}, uniqueConstraints = {@UniqueConstraint(name = "uq_phone_suppliers",
        columnNames = {
                "supplier_id",
                "phone_id"})})
public class PhoneSupplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "phone_id", nullable = false)
    private PhoneNumber phone;


}