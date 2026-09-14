package com.solairy.homedesk.inventory.controller;

import com.solairy.homedesk.inventory.service.InventoryLotService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/inventory-lots")
@RequiredArgsConstructor
@Tag(name = "Inventory Lots", description = "Endpoints for inventory lot management")
public class InventoryLotController {

    private final InventoryLotService inventoryLotService;

}
