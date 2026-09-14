package com.solairy.homedesk.inventory.service.impl;

import com.solairy.homedesk.inventory.repository.InventoryRepository;
import com.solairy.homedesk.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

}
