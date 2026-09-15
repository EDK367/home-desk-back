package com.solairy.homedesk.purchases.repository;

import com.solairy.homedesk.purchases.model.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    Page<Purchase> findBySupplierId(Long supplierId, Pageable pageable);

    Page<Purchase> findByStatusId(Long statusId, Pageable pageable);

    Page<Purchase> findByPurchaseDateBetween(OffsetDateTime startDate, OffsetDateTime endDate, Pageable pageable);

    List<Purchase> findBySupplierId(Long supplierId);
}
