package com.solairy.homedesk.sales.service;

import com.solairy.homedesk.common.response.PageResponse;
import com.solairy.homedesk.sales.dto.request.CustomerRequest;
import com.solairy.homedesk.sales.dto.response.CustomerResponse;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    PageResponse<CustomerResponse> findAllSales(Pageable pageable);

    PageResponse<CustomerResponse> findAllAdmin(Pageable pageable);

    CustomerResponse findById(Long id);

    CustomerResponse create(CustomerRequest request);

    CustomerResponse update(Long id, CustomerRequest request);

    void delete(Long id);
}
