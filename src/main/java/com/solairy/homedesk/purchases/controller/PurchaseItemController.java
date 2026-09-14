package com.solairy.homedesk.purchases.controller;

import com.solairy.homedesk.purchases.service.PurchaseItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/purchase-items")
@RequiredArgsConstructor
@Tag(name = "Purchase Items", description = "Endpoints for purchase item management")
public class PurchaseItemController {

    private final PurchaseItemService purchaseItemService;

}
