package com.solairy.homedesk.reports.repository;

import com.solairy.homedesk.reports.views.VwTop5SuppliersByPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VwTop5SuppliersByPurchaseRepository extends JpaRepository<VwTop5SuppliersByPurchase, Long> {
}
