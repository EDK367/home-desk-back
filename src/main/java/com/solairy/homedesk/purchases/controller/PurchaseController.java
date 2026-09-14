package com.solairy.homedesk.purchases.controller;

import com.solairy.homedesk.purchases.service.PurchaseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/purchases")
@RequiredArgsConstructor
@Tag(name = "Purchases", description = "Endpoints for purchase management")
public class PurchaseController {

    private final PurchaseService purchaseService;

}
