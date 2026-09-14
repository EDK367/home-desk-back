package com.solairy.homedesk.payment.service.impl;

import com.solairy.homedesk.payment.repository.SalePaymentRepository;
import com.solairy.homedesk.payment.service.SalePaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalePaymentServiceImpl implements SalePaymentService {

    private final SalePaymentRepository salePaymentRepository;

}
