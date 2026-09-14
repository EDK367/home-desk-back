package com.solairy.homedesk.suppliers.repository;

import com.solairy.homedesk.suppliers.model.PhoneSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneSupplierRepository extends JpaRepository<PhoneSupplier, Long> {
}
