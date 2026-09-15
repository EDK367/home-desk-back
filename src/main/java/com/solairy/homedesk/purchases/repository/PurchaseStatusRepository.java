package com.solairy.homedesk.purchases.repository;

import com.solairy.homedesk.purchases.model.PurchaseStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseStatusRepository extends JpaRepository<PurchaseStatus, Long> {

    Optional<PurchaseStatus> findByName(String name);

    List<PurchaseStatus> findAllByIsActive(Boolean isActive);

    Page<PurchaseStatus> findAllByIsActive(Boolean isActive, Pageable pageable);
}
