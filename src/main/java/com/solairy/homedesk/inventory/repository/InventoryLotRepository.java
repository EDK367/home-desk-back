package com.solairy.homedesk.inventory.repository;

import com.solairy.homedesk.inventory.model.InventoryLot;
import com.solairy.homedesk.purchases.model.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryLotRepository extends JpaRepository<InventoryLot, Long> {

    Optional<InventoryLot> findByPurchaseItemId(Long purchaseItemId);

    List<InventoryLot> findByPurchaseItemIn(Collection<PurchaseItem> purchaseItems);

    List<InventoryLot> findByProductId(Long productId);

    List<InventoryLot> findByProductIdAndQuantityRemainingGreaterThanOrderByReceivedAtAsc(Long productId, Integer minQuantity);

    List<InventoryLot> findByProductIdAndQuantityRemainingGreaterThanOrderByReceivedAtDesc(Long productId, Integer minQuantity);
}
