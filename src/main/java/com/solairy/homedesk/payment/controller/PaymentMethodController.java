package com.solairy.homedesk.payment.controller;

import com.solairy.homedesk.payment.service.PaymentMethodService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment-methods")
@RequiredArgsConstructor
@Tag(name = "Payment Methods", description = "Endpoints for payment method management")
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

}
