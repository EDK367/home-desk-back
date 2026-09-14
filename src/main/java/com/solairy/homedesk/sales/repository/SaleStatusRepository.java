package com.solairy.homedesk.sales.repository;

import com.solairy.homedesk.sales.model.SaleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleStatusRepository extends JpaRepository<SaleStatus, Long> {
}
