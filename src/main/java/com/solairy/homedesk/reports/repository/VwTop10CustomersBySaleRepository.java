package com.solairy.homedesk.reports.repository;

import com.solairy.homedesk.reports.views.VwTop10CustomersBySale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VwTop10CustomersBySaleRepository extends JpaRepository<VwTop10CustomersBySale, Long> {
}
