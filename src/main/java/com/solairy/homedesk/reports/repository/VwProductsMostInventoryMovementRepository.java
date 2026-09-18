package com.solairy.homedesk.reports.repository;

import com.solairy.homedesk.reports.views.VwProductsMostInventoryMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VwProductsMostInventoryMovementRepository extends JpaRepository<VwProductsMostInventoryMovement, Long> {
}
