package com.solairy.homedesk.inventory.service;

import com.solairy.homedesk.common.response.PageResponse;
import com.solairy.homedesk.inventory.dto.response.InventoryMovementResponse;
import org.springframework.data.domain.Pageable;

import java.time.OffsetDateTime;

public interface InventoryMovementService {

    PageResponse<InventoryMovementResponse> findAll(Pageable pageable);

    PageResponse<InventoryMovementResponse> findByProduct(Long productId, Pageable pageable);

    PageResponse<InventoryMovementResponse> findByType(String movementType, Pageable pageable);

    PageResponse<InventoryMovementResponse> findByDateRange(OffsetDateTime start, OffsetDateTime end, Pageable pageable);

    PageResponse<InventoryMovementResponse> findByProductAndDateRange(Long productId, OffsetDateTime start, OffsetDateTime end, Pageable pageable);

    InventoryMovementResponse findById(Long id);
}
