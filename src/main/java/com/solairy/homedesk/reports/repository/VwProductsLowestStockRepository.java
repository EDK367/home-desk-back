package com.solairy.homedesk.reports.repository;

import com.solairy.homedesk.reports.views.VwProductsLowestStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VwProductsLowestStockRepository extends JpaRepository<VwProductsLowestStock, Long> {
}
