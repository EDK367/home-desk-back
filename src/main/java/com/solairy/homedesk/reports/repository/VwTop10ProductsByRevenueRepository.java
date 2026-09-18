package com.solairy.homedesk.reports.repository;

import com.solairy.homedesk.reports.views.VwTop10ProductsByRevenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VwTop10ProductsByRevenueRepository extends JpaRepository<VwTop10ProductsByRevenue, Long> {
}
