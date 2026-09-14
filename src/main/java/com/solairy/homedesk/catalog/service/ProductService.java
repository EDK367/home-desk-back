package com.solairy.homedesk.catalog.service;

import com.solairy.homedesk.catalog.dto.request.*;
import com.solairy.homedesk.catalog.dto.response.ProductResponse;
import com.solairy.homedesk.catalog.dto.response.ProductStockResponse;
import com.solairy.homedesk.common.response.PageResponse;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    PageResponse<ProductResponse> findAll(Pageable pageable);

    PageResponse<ProductResponse> findAllProductAdmin(Pageable pageable);

    ProductResponse findById(Long id);

    ProductStockResponse getStock(Long id);

    ProductResponse create(ProductRequest request);

    ProductResponse update(Long id, ProductRequest request);

    ProductResponse changeSalePrice(Long id, ChangeSalePriceRequest request);

    ProductResponse changeMinStock(Long id, ChangeMinStockRequest request);

    ProductResponse changeCategory(Long id, ChangeCategoryRequest request);

    ProductResponse updateStatus(Long id, boolean active);

    ProductResponse adjustStock(Long id, ManualStockAdjustmentRequest request);

    void delete(Long id);
}
