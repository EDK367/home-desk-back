package com.solairy.homedesk.suppliers.repository;

import com.solairy.homedesk.suppliers.model.SupplierProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierProductRepository extends JpaRepository<SupplierProduct, Long> {
}
