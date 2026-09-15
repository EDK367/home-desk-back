package com.solairy.homedesk.inventory.repository;

import com.solairy.homedesk.inventory.model.InventoryLot;
import com.solairy.homedesk.inventory.model.InventoryMovement;
import com.solairy.homedesk.inventory.model.InventoryMovementLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface InventoryMovementLotRepository extends JpaRepository<InventoryMovementLot, Long> {

    List<InventoryMovementLot> findByMovement(InventoryMovement movement);

    List<InventoryMovementLot> findByMovementIn(Collection<InventoryMovement> movements);

    List<InventoryMovementLot> findByLot(InventoryLot lot);
}
