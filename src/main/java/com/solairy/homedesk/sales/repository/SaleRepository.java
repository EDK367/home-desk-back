package com.solairy.homedesk.sales.repository;

import com.solairy.homedesk.sales.model.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Optional;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    boolean existsByInvoiceNumber(String invoiceNumber);

    Optional<Sale> findByInvoiceNumber(String invoiceNumber);

    Page<Sale> findByCustomerId(Long customerId, Pageable pageable);

    Page<Sale> findByStatusId(Long statusId, Pageable pageable);

    Page<Sale> findBySaleDateBetween(OffsetDateTime start, OffsetDateTime end, Pageable pageable);
}
