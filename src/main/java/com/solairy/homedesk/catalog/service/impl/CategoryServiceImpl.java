package com.solairy.homedesk.catalog.service.impl;

import com.solairy.homedesk.catalog.dto.request.CategoryRequest;
import com.solairy.homedesk.catalog.dto.response.CategoryResponse;
import com.solairy.homedesk.catalog.mapper.CategoryMapper;
import com.solairy.homedesk.catalog.model.Category;
import com.solairy.homedesk.catalog.repository.CategoryRepository;
import com.solairy.homedesk.catalog.service.CategoryService;
import com.solairy.homedesk.common.exception.GlobalException;
import com.solairy.homedesk.common.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public PageResponse<CategoryResponse> findAll(Pageable pageable) {
        Page<Category> page = repository.findAllByIsActive(true, pageable);
        return PageResponse.from(page, mapper::toCategoryResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<CategoryResponse> findAllProductAdmin(Pageable pageable) {
        Page<Category> page = repository.findAll(pageable);
        return PageResponse.from(page, mapper::toCategoryResponse);
    }

    @Override
    @Transactional
    public CategoryResponse create(CategoryRequest request) {
        String name = request.name().trim();
        if (repository.existsByName(name)) {
            throw new GlobalException(HttpStatus.CONFLICT, "Ya existe una categoría con el nombre: " + name);
        }

        Category category = new Category();
        category.setName(name);
        category.setDescription(request.description() != null ? request.description().trim() : null);
        category.setIsActive(true);
        category.setCreatedAt(OffsetDateTime.now());
        category.setUpdatedAt(OffsetDateTime.now());

        Category saved = repository.save(category);
        return mapper.toCategoryResponse(saved);
    }

    @Override
    @Transactional
    public CategoryResponse update(Long id, CategoryRequest request) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Categoría no encontrada con id: " + id));

        if (Boolean.FALSE.equals(category.getIsActive())) {
            throw new GlobalException(HttpStatus.CONFLICT, "No se puede actualizar una categoría inactiva");
        }

        String name = request.name().trim();
        if (repository.existsByNameAndIdNot(name, id)) {
            throw new GlobalException(HttpStatus.CONFLICT, "Ya existe una categoría con el nombre: " + name);
        }

        category.setName(name);
        category.setDescription(request.description() != null ? request.description().trim() : null);
        category.setUpdatedAt(OffsetDateTime.now());

        Category saved = repository.save(category);
        return mapper.toCategoryResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Categoría no encontrada con id: " + id));

        if (Boolean.FALSE.equals(category.getIsActive())) {
            throw new GlobalException(HttpStatus.CONFLICT, "La categoría ya se encuentra inactiva");
        }

        category.setIsActive(false);
        category.setUpdatedAt(OffsetDateTime.now());
        repository.save(category);
    }
}
