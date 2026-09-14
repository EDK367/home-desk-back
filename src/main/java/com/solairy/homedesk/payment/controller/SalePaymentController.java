package com.solairy.homedesk.payment.controller;

import com.solairy.homedesk.payment.service.SalePaymentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sale-payments")
@RequiredArgsConstructor
@Tag(name = "Sale Payments", description = "Endpoints for sale payment management")
public class SalePaymentController {

    private final SalePaymentService salePaymentService;

}
