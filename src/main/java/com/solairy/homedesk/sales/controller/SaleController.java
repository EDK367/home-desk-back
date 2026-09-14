package com.solairy.homedesk.sales.controller;

import com.solairy.homedesk.sales.service.SaleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sales")
@RequiredArgsConstructor
@Tag(name = "Sales", description = "Endpoints for sale management")
public class SaleController {

    private final SaleService saleService;

}
