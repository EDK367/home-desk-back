package com.solairy.homedesk.reports.repository;

import com.solairy.homedesk.reports.views.VwTop10ProductsSold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VwTop10ProductsSoldRepository extends JpaRepository<VwTop10ProductsSold, Long> {
}
