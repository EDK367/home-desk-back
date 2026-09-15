package com.solairy.homedesk.inventory.repository;

import com.solairy.homedesk.inventory.model.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findByProductId(Long productId);

    @Query("SELECT i FROM Inventory i WHERE i.product.id IN :productIds")
    List<Inventory> findByProductIdIn(@Param("productIds") List<Long> productIds);

    Page<Inventory> findByProductCategoryId(Long categoryId, Pageable pageable);

    @Query("SELECT i FROM Inventory i WHERE i.quantityOnHand <= i.product.minStockLevel AND i.product.isActive = true")
    Page<Inventory> findLowStock(Pageable pageable);

    @Query("SELECT i FROM Inventory i WHERE i.quantityOnHand <= i.product.minStockLevel AND i.product.isActive = true")
    List<Inventory> findLowStock();
}
