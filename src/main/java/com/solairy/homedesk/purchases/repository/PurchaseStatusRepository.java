package com.solairy.homedesk.purchases.repository;

import com.solairy.homedesk.purchases.model.PurchaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseStatusRepository extends JpaRepository<PurchaseStatus, Long> {
}
