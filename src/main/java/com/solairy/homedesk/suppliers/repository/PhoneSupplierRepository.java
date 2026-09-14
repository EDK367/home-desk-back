package com.solairy.homedesk.suppliers.repository;

import com.solairy.homedesk.suppliers.model.PhoneSupplier;
import com.solairy.homedesk.suppliers.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneSupplierRepository extends JpaRepository<PhoneSupplier, Long> {

    List<PhoneSupplier> findBySupplier(Supplier supplier);

    void deleteBySupplier(Supplier supplier);
}
