package com.solairy.homedesk.purchases.service;

import com.solairy.homedesk.purchases.dto.response.PurchaseStatusResponse;

import java.util.List;

public interface PurchaseStatusService {

    List<PurchaseStatusResponse> findAll();

    PurchaseStatusResponse findById(Long id);
}
