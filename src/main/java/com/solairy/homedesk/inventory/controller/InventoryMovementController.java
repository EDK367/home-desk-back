package com.solairy.homedesk.inventory.controller;

import com.solairy.homedesk.inventory.service.InventoryMovementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/inventory-movements")
@RequiredArgsConstructor
@Tag(name = "Inventory Movements", description = "Endpoints for inventory movement management")
public class InventoryMovementController {

    private final InventoryMovementService inventoryMovementService;

}
