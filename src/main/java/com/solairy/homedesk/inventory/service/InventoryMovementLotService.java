package com.solairy.homedesk.inventory.service;

import com.solairy.homedesk.inventory.dto.response.InventoryMovementLotResponse;

import java.util.List;

public interface InventoryMovementLotService {

    List<InventoryMovementLotResponse> findByMovementId(Long movementId);

    List<InventoryMovementLotResponse> findByLotId(Long lotId);

    InventoryMovementLotResponse findById(Long id);
}
