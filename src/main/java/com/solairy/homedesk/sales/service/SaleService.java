package com.solairy.homedesk.sales.service;

import com.solairy.homedesk.common.response.PageResponse;
import com.solairy.homedesk.sales.dto.request.SaleRequest;
import com.solairy.homedesk.sales.dto.request.SaleStatusChangeRequest;
import com.solairy.homedesk.sales.dto.response.SaleResponse;
import org.springframework.data.domain.Pageable;

public interface SaleService {

    PageResponse<SaleResponse> findAll(Pageable pageable);

    SaleResponse findById(Long id);

    SaleResponse findByInvoiceNumber(String invoiceNumber);

    PageResponse<SaleResponse> findByCustomer(Long customerId, Pageable pageable);

    PageResponse<SaleResponse> findByStatus(Long statusId, Pageable pageable);

    SaleResponse create(SaleRequest request);

    SaleResponse updateStatus(Long id, SaleStatusChangeRequest request);

    void cancel(Long id);
}
