package com.solairy.homedesk.inventory.service.impl;

import com.solairy.homedesk.inventory.repository.InventoryMovementRepository;
import com.solairy.homedesk.inventory.service.InventoryMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryMovementServiceImpl implements InventoryMovementService {

    private final InventoryMovementRepository inventoryMovementRepository;

}
