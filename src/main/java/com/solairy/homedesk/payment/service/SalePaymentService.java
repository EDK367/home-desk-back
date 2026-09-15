package com.solairy.homedesk.payment.service;

import com.solairy.homedesk.common.response.PageResponse;
import com.solairy.homedesk.payment.dto.request.SalePaymentRequest;
import com.solairy.homedesk.payment.dto.response.SalePaymentResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SalePaymentService {

    PageResponse<SalePaymentResponse> findBySale(Long saleId, Pageable pageable);

    List<SalePaymentResponse> findBySaleId(Long saleId);

    SalePaymentResponse findById(Long id);

    SalePaymentResponse create(SalePaymentRequest request);

    void delete(Long id);
}
