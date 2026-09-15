package com.solairy.homedesk.inventory.service;

import com.solairy.homedesk.common.response.PageResponse;
import com.solairy.homedesk.inventory.dto.response.InventoryLotResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InventoryLotService {

    PageResponse<InventoryLotResponse> findAll(Pageable pageable);

    PageResponse<InventoryLotResponse> findByProduct(Long productId, Pageable pageable);

    List<InventoryLotResponse> findActiveLotsByProduct(Long productId);

    InventoryLotResponse findById(Long id);
}
