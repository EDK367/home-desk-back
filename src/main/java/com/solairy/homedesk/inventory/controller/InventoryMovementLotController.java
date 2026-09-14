package com.solairy.homedesk.inventory.controller;

import com.solairy.homedesk.inventory.service.InventoryMovementLotService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/inventory-movement-lots")
@RequiredArgsConstructor
@Tag(name = "Inventory Movement Lots", description = "Endpoints for inventory movement lot management")
public class InventoryMovementLotController {

    private final InventoryMovementLotService inventoryMovementLotService;

}
