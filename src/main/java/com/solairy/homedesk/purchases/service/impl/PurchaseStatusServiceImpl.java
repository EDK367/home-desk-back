package com.solairy.homedesk.purchases.service.impl;

import com.solairy.homedesk.purchases.repository.PurchaseStatusRepository;
import com.solairy.homedesk.purchases.service.PurchaseStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseStatusServiceImpl implements PurchaseStatusService {

    private final PurchaseStatusRepository purchaseStatusRepository;

}
