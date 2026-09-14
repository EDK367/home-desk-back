package com.solairy.homedesk.catalog.service;

import com.solairy.homedesk.catalog.dto.request.CategoryRequest;
import com.solairy.homedesk.catalog.dto.response.CategoryResponse;
import com.solairy.homedesk.common.response.PageResponse;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    PageResponse<CategoryResponse> findAll(Pageable pageable);

    PageResponse<CategoryResponse> findAllProductAdmin(Pageable pageable);

    CategoryResponse create(CategoryRequest request);

    CategoryResponse update(Long id, CategoryRequest request);

    void delete(Long id);
}
