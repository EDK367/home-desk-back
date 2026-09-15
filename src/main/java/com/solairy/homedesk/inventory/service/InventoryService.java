package com.solairy.homedesk.inventory.service;

import com.solairy.homedesk.common.response.PageResponse;
import com.solairy.homedesk.inventory.dto.response.InventoryResponse;
import org.springframework.data.domain.Pageable;

public interface InventoryService {

    PageResponse<InventoryResponse> findAll(Pageable pageable);

    PageResponse<InventoryResponse> findLowStock(Pageable pageable);

    PageResponse<InventoryResponse> findByCategory(Long categoryId, Pageable pageable);

    InventoryResponse findById(Long id);

    InventoryResponse findByProductId(Long productId);

    InventoryResponse adjustStock(Long productId, com.solairy.homedesk.inventory.dto.request.ManualStockAdjustmentRequest request);
}
