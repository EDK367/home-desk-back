package com.solairy.homedesk.suppliers.controller;

import com.solairy.homedesk.common.response.ApiResponse;
import com.solairy.homedesk.common.response.PageResponse;
import com.solairy.homedesk.suppliers.dto.request.SupplierProductRequest;
import com.solairy.homedesk.suppliers.dto.request.SupplierProductUpdateRequest;
import com.solairy.homedesk.suppliers.dto.response.SupplierProductResponse;
import com.solairy.homedesk.suppliers.service.SupplierProductService;
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
@RequestMapping("/api/v1/supplier-products")
@RequiredArgsConstructor
@Tag(name = "Supplier Products", description = "Endpoints para la gestión de productos por proveedor")
public class SupplierProductController {

    private final SupplierProductService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'PURCHASING')")
    public ResponseEntity<ApiResponse<PageResponse<SupplierProductResponse>>> findAll(
            @ParameterObject
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(required = false) Long supplierId,
            @RequestParam(required = false) Long productId
    ) {
        PageResponse<SupplierProductResponse> response = service.findAll(pageable, supplierId, productId);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Productos de proveedores obtenidos exitosamente",
                response
        ));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'PURCHASING')")
    public ResponseEntity<ApiResponse<SupplierProductResponse>> findById(@PathVariable Long id) {
        SupplierProductResponse response = service.findById(id);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Producto de proveedor obtenido exitosamente",
                response
        ));
    }

    @GetMapping("/supplier/{supplierId}")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'PURCHASING')")
    public ResponseEntity<ApiResponse<PageResponse<SupplierProductResponse>>> findBySupplier(
            @PathVariable Long supplierId,
            @ParameterObject
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PageResponse<SupplierProductResponse> response = service.findBySupplier(supplierId, pageable);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Productos del proveedor obtenidos exitosamente",
                response
        ));
    }

    @GetMapping("/product/{productId}")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'PURCHASING')")
    public ResponseEntity<ApiResponse<PageResponse<SupplierProductResponse>>> findByProduct(
            @PathVariable Long productId,
            @ParameterObject
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PageResponse<SupplierProductResponse> response = service.findByProduct(productId, pageable);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Proveedores del producto obtenidos exitosamente",
                response
        ));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'PURCHASING')")
    public ResponseEntity<ApiResponse<SupplierProductResponse>> create(
            @Valid @RequestBody SupplierProductRequest request
    ) {
        SupplierProductResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(
                HttpStatus.CREATED.value(),
                "Producto asociado al proveedor exitosamente",
                response
        ));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'PURCHASING')")
    public ResponseEntity<ApiResponse<SupplierProductResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody SupplierProductUpdateRequest request
    ) {
        SupplierProductResponse response = service.update(id, request);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Relación proveedor-producto actualizada exitosamente",
                response
        ));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'PURCHASING')")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Relación proveedor-producto eliminada exitosamente",
                null
        ));
    }

    @DeleteMapping("/supplier/{supplierId}/product/{productId}")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'PURCHASING')")
    public ResponseEntity<ApiResponse<Void>> deleteBySupplierAndProduct(
            @PathVariable Long supplierId,
            @PathVariable Long productId
    ) {
        service.deleteBySupplierAndProduct(supplierId, productId);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Relación proveedor-producto eliminada exitosamente",
                null
        ));
    }
}
