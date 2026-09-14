package com.solairy.homedesk.purchases.service.impl;

import com.solairy.homedesk.purchases.repository.PurchaseRepository;
import com.solairy.homedesk.purchases.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;

}
