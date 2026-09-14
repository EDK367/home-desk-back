package com.solairy.homedesk.catalog.controller;

import com.solairy.homedesk.catalog.dto.request.CategoryRequest;
import com.solairy.homedesk.catalog.dto.response.CategoryResponse;
import com.solairy.homedesk.catalog.service.CategoryService;
import com.solairy.homedesk.common.response.ApiResponse;
import com.solairy.homedesk.common.response.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@Tag(name = "Categories", description = "Endpoints para la gestión de categorías")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<CategoryResponse>>> findAll(
            @ParameterObject
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PageResponse<CategoryResponse> response = categoryService.findAll(pageable);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Categorías obtenidas exitosamente",
                response
        ));
    }

    @GetMapping({"/admin", "/findAllProductAdmin"})
    @PreAuthorize("hasRole('ADMINISTRATION')")
    public ResponseEntity<ApiResponse<PageResponse<CategoryResponse>>> findAllProductAdmin(
            @ParameterObject
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PageResponse<CategoryResponse> response = categoryService.findAllProductAdmin(pageable);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Categorías obtenidas exitosamente",
                response
        ));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRATION')")
    public ResponseEntity<ApiResponse<CategoryResponse>> create(
            @Valid @RequestBody CategoryRequest request
    ) {
        CategoryResponse response = categoryService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(
                HttpStatus.CREATED.value(),
                "Categoría creada exitosamente",
                response
        ));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATION')")
    public ResponseEntity<ApiResponse<CategoryResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequest request
    ) {
        CategoryResponse response = categoryService.update(id, request);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Categoría actualizada exitosamente",
                response
        ));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATION')")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long id
    ) {
        categoryService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Categoría desactivada exitosamente",
                null
        ));
    }
}
