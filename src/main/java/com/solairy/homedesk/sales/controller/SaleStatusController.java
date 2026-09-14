package com.solairy.homedesk.sales.controller;

import com.solairy.homedesk.sales.service.SaleStatusService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sale-statuses")
@RequiredArgsConstructor
@Tag(name = "Sale Statuses", description = "Endpoints for sale status management")
public class SaleStatusController {

    private final SaleStatusService saleStatusService;

}
