package com.solairy.homedesk.inventory.repository;

import com.solairy.homedesk.inventory.model.InventoryMovement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface InventoryMovementRepository extends JpaRepository<InventoryMovement, Long> {

    List<InventoryMovement> findByProductId(Long productId);

    Page<InventoryMovement> findByProductId(Long productId, Pageable pageable);

    Page<InventoryMovement> findByMovementType(String movementType, Pageable pageable);

    Page<InventoryMovement> findByCreatedAtBetween(OffsetDateTime start, OffsetDateTime end, Pageable pageable);

    Page<InventoryMovement> findByProductIdAndCreatedAtBetween(Long productId, OffsetDateTime start, OffsetDateTime end, Pageable pageable);

    Page<InventoryMovement> findByProductIdAndMovementType(Long productId, String movementType, Pageable pageable);

    List<InventoryMovement> findByReferenceTypeAndReferenceId(String referenceType, Long referenceId);
}
