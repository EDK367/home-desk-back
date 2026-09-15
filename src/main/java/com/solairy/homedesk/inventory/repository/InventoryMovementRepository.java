package com.solairy.homedesk.inventory.repository;

import com.solairy.homedesk.inventory.model.InventoryMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryMovementRepository extends JpaRepository<InventoryMovement, Long> {

    List<InventoryMovement> findByProductId(Long productId);

    List<InventoryMovement> findByReferenceTypeAndReferenceId(String referenceType, Long referenceId);
}
