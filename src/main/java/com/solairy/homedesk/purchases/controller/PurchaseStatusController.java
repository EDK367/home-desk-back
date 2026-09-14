package com.solairy.homedesk.purchases.controller;

import com.solairy.homedesk.purchases.service.PurchaseStatusService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/purchase-statuses")
@RequiredArgsConstructor
@Tag(name = "Purchase Statuses", description = "Endpoints for purchase status management")
public class PurchaseStatusController {

    private final PurchaseStatusService purchaseStatusService;

}
