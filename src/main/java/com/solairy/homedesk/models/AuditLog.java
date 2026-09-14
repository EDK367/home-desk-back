package com.solairy.homedesk.models;

import com.solairy.homedesk.auth.model.User;
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
@Table(name = "audit_logs", schema = "public", indexes = {
        @Index(name = "idx_audit_logs_user_id",
                columnList = "user_id"),
        @Index(name = "idx_audit_logs_action",
                columnList = "action"),
        @Index(name = "idx_audit_logs_module",
                columnList = "module"),
        @Index(name = "idx_audit_logs_created_at",
                columnList = "created_at")})
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Size(max = 30)
    @NotNull
    @Column(name = "action", nullable = false, length = 30)
    private String action;

    @Size(max = 50)
    @NotNull
    @Column(name = "module", nullable = false, length = 50)
    private String module;

    @Size(max = 50)
    @Column(name = "entity_name", length = 50)
    private String entityName;

    @Size(max = 50)
    @Column(name = "entity_id", length = 50)
    private String entityId;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;


}