package com.solairy.homedesk.sales.repository;

import com.solairy.homedesk.sales.model.SaleStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaleStatusRepository extends JpaRepository<SaleStatus, Long> {

    Optional<SaleStatus> findByName(String name);

    List<SaleStatus> findAllByIsActive(Boolean isActive);

    Page<SaleStatus> findAllByIsActive(Boolean isActive, Pageable pageable);
}
