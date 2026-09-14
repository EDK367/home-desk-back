package com.solairy.homedesk.suppliers.service;

import com.solairy.homedesk.common.response.PageResponse;
import com.solairy.homedesk.suppliers.dto.request.SupplierRequest;
import com.solairy.homedesk.suppliers.dto.response.SupplierResponse;
import org.springframework.data.domain.Pageable;

public interface SupplierService {

    PageResponse<SupplierResponse> findAll(Pageable pageable, boolean includeInactive);

    SupplierResponse findById(Long id);

    SupplierResponse create(SupplierRequest request);

    SupplierResponse update(Long id, SupplierRequest request);

    void delete(Long id);
}
