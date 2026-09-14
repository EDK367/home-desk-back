package com.solairy.homedesk.sales.controller;

import com.solairy.homedesk.sales.service.SaleItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sale-items")
@RequiredArgsConstructor
@Tag(name = "Sale Items", description = "Endpoints for sale item management")
public class SaleItemController {

    private final SaleItemService saleItemService;

}
