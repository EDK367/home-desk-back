package com.solairy.homedesk.reports.repository;

import com.solairy.homedesk.reports.views.VwProductsMostFrequentlyPurchased;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VwProductsMostFrequentlyPurchasedRepository extends JpaRepository<VwProductsMostFrequentlyPurchased, Long> {
}
