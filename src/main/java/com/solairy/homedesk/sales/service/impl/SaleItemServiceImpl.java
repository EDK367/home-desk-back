package com.solairy.homedesk.sales.service.impl;

import com.solairy.homedesk.sales.repository.SaleItemRepository;
import com.solairy.homedesk.sales.service.SaleItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleItemServiceImpl implements SaleItemService {

    private final SaleItemRepository saleItemRepository;

}
