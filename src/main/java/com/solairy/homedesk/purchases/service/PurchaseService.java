package com.solairy.homedesk.purchases.service;

import com.solairy.homedesk.common.response.PageResponse;
import com.solairy.homedesk.purchases.dto.request.PurchaseRequest;
import com.solairy.homedesk.purchases.dto.request.PurchaseStatusChangeRequest;
import com.solairy.homedesk.purchases.dto.response.PurchaseResponse;
import org.springframework.data.domain.Pageable;

public interface PurchaseService {

    PageResponse<PurchaseResponse> findAll(Pageable pageable);

    PurchaseResponse findById(Long id);

    PageResponse<PurchaseResponse> findBySupplier(Long supplierId, Pageable pageable);

    PageResponse<PurchaseResponse> findByStatus(Long statusId, Pageable pageable);

    PurchaseResponse create(PurchaseRequest request);

    PurchaseResponse updateStatus(Long id, PurchaseStatusChangeRequest request);

    void cancel(Long id);
}
