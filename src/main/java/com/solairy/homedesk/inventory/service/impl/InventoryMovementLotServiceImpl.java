package com.solairy.homedesk.inventory.service.impl;

import com.solairy.homedesk.inventory.repository.InventoryMovementLotRepository;
import com.solairy.homedesk.inventory.service.InventoryMovementLotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryMovementLotServiceImpl implements InventoryMovementLotService {

    private final InventoryMovementLotRepository inventoryMovementLotRepository;

}
