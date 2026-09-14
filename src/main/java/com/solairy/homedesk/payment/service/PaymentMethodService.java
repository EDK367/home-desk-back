package com.solairy.homedesk.payment.service;

import com.solairy.homedesk.common.response.PageResponse;
import com.solairy.homedesk.payment.dto.request.PaymentMethodRequest;
import com.solairy.homedesk.payment.dto.response.PaymentMethodResponse;
import org.springframework.data.domain.Pageable;

public interface PaymentMethodService {

    PageResponse<PaymentMethodResponse> findAllSales(Pageable pageable);

    PageResponse<PaymentMethodResponse> findAllAdmin(Pageable pageable);

    PaymentMethodResponse findById(Long id);

    PaymentMethodResponse create(PaymentMethodRequest request);

    PaymentMethodResponse update(Long id, PaymentMethodRequest request);

    void delete(Long id);
}
