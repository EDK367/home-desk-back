package com.solairy.homedesk.sales.service.impl;

import com.solairy.homedesk.sales.repository.SaleRepository;
import com.solairy.homedesk.sales.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;

}
