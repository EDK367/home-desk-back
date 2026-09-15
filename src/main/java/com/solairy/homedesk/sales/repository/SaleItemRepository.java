package com.solairy.homedesk.sales.repository;

import com.solairy.homedesk.sales.model.SaleItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {

    List<SaleItem> findBySaleId(Long saleId);

    Page<SaleItem> findBySaleId(Long saleId, Pageable pageable);

    List<SaleItem> findByProductId(Long productId);
}
