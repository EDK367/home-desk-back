package com.solairy.homedesk.payment.service.impl;

import com.solairy.homedesk.payment.repository.PaymentMethodRepository;
import com.solairy.homedesk.payment.service.PaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

}
