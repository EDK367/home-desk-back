package com.solairy.homedesk.purchases.service.impl;

import com.solairy.homedesk.purchases.repository.PurchaseItemRepository;
import com.solairy.homedesk.purchases.service.PurchaseItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseItemServiceImpl implements PurchaseItemService {

    private final PurchaseItemRepository purchaseItemRepository;

}
