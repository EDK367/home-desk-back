package com.solairy.homedesk.inventory.repository;

import com.solairy.homedesk.inventory.model.InventoryLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryLotRepository extends JpaRepository<InventoryLot, Long> {
}
