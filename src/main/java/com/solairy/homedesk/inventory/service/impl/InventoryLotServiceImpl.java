package com.solairy.homedesk.inventory.service.impl;

import com.solairy.homedesk.inventory.repository.InventoryLotRepository;
import com.solairy.homedesk.inventory.service.InventoryLotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryLotServiceImpl implements InventoryLotService {

    private final InventoryLotRepository inventoryLotRepository;

}
