package com.solairy.homedesk.purchases.repository;

import com.solairy.homedesk.purchases.model.PurchaseItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Long> {

    List<PurchaseItem> findByPurchaseId(Long purchaseId);

    Page<PurchaseItem> findByPurchaseId(Long purchaseId, Pageable pageable);

    List<PurchaseItem> findByProductId(Long productId);
}
