package com.solairy.homedesk.inventory.repository;

import com.solairy.homedesk.inventory.model.InventoryMovementLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryMovementLotRepository extends JpaRepository<InventoryMovementLot, Long> {
}
