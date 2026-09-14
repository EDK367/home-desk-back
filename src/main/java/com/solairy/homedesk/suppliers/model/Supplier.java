package com.solairy.homedesk.suppliers.model;

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
@Table(name = "suppliers", schema = "public", indexes = {
        @Index(name = "idx_suppliers_name",
                columnList = "name"),
        @Index(name = "idx_suppliers_tax_number",
                columnList = "tax_number")}, uniqueConstraints = {@UniqueConstraint(name = "suppliers_tax_number_key",
        columnNames = {"tax_number"})})
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 150)
    @NotNull
    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Size(max = 30)
    @Column(name = "tax_number", length = 30)
    private String taxNumber;

    @Size(max = 150)
    @Column(name = "contact_name", length = 150)
    private String contactName;

    @Size(max = 150)
    @Column(name = "email", length = 150)
    private String email;

    @Size(max = 255)
    @Column(name = "address")
    private String address;

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