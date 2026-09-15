package com.solairy.homedesk.suppliers.repository;

import com.solairy.homedesk.suppliers.model.SupplierProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierProductRepository extends JpaRepository<SupplierProduct, Long> {

    Page<SupplierProduct> findBySupplierId(Long supplierId, Pageable pageable);

    Page<SupplierProduct> findByProductId(Long productId, Pageable pageable);

    Optional<SupplierProduct> findBySupplierIdAndProductId(Long supplierId, Long productId);

    boolean existsBySupplierIdAndProductId(Long supplierId, Long productId);

    boolean existsBySupplierIdAndProductIdAndIdNot(Long supplierId, Long productId, Long id);

    List<SupplierProduct> findBySupplierId(Long supplierId);

    List<SupplierProduct> findByProductId(Long productId);

    void deleteBySupplierIdAndProductId(Long supplierId, Long productId);
}
