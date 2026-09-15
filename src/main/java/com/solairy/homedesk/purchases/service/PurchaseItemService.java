package com.solairy.homedesk.purchases.service;

import com.solairy.homedesk.purchases.dto.response.PurchaseItemResponse;

import java.util.List;

public interface PurchaseItemService {

    List<PurchaseItemResponse> findByPurchaseId(Long purchaseId);

    PurchaseItemResponse findById(Long id);
}
