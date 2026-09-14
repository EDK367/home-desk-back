package com.solairy.homedesk.sales.service.impl;

import com.solairy.homedesk.sales.repository.SaleStatusRepository;
import com.solairy.homedesk.sales.service.SaleStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleStatusServiceImpl implements SaleStatusService {

    private final SaleStatusRepository saleStatusRepository;

}
