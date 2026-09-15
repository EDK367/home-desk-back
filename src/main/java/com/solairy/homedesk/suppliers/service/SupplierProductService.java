package com.solairy.homedesk.suppliers.service;

import com.solairy.homedesk.common.response.PageResponse;
import com.solairy.homedesk.suppliers.dto.request.SupplierProductRequest;
import com.solairy.homedesk.suppliers.dto.request.SupplierProductUpdateRequest;
import com.solairy.homedesk.suppliers.dto.response.SupplierProductResponse;
import org.springframework.data.domain.Pageable;

public interface SupplierProductService {

    PageResponse<SupplierProductResponse> findAll(Pageable pageable, Long supplierId, Long productId);

    PageResponse<SupplierProductResponse> findBySupplier(Long supplierId, Pageable pageable);

    PageResponse<SupplierProductResponse> findByProduct(Long productId, Pageable pageable);

    SupplierProductResponse findById(Long id);

    SupplierProductResponse create(SupplierProductRequest request);

    SupplierProductResponse update(Long id, SupplierProductUpdateRequest request);

    void delete(Long id);

    void deleteBySupplierAndProduct(Long supplierId, Long productId);
}
