package com.solairy.homedesk.suppliers.repository;

import com.solairy.homedesk.suppliers.model.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    boolean existsByTaxNumber(String taxNumber);

    boolean existsByTaxNumberAndIdNot(String taxNumber, Long id);

    Page<Supplier> findAllByIsActive(Boolean isActive, Pageable pageable);
}
