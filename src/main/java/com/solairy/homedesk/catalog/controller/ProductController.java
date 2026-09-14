package com.solairy.homedesk.catalog.controller;

import com.solairy.homedesk.catalog.dto.request.*;
import com.solairy.homedesk.catalog.dto.response.ProductResponse;
import com.solairy.homedesk.catalog.dto.response.ProductStockResponse;
import com.solairy.homedesk.catalog.service.ProductService;
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
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Products", description = "Endpoints para la gestión de productos")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'PURCHASING', 'INVENTORY', 'SALES')")
    public ResponseEntity<ApiResponse<PageResponse<ProductResponse>>> findAll(
            @ParameterObject
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PageResponse<ProductResponse> response = productService.findAll(pageable);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Productos obtenidos exitosamente",
                response
        ));
    }

    @GetMapping({"/admin"})
    @PreAuthorize("hasRole('ADMINISTRATION')")
    public ResponseEntity<ApiResponse<PageResponse<ProductResponse>>> findAllProductAdmin(
            @ParameterObject
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PageResponse<ProductResponse> response = productService.findAllProductAdmin(pageable);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Productos obtenidos exitosamente",
                response
        ));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'PURCHASING', 'INVENTORY', 'SALES')")
    public ResponseEntity<ApiResponse<ProductResponse>> findById(@PathVariable Long id) {
        ProductResponse response = productService.findById(id);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Producto obtenido exitosamente",
                response
        ));
    }

    @GetMapping("/{id}/stock")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'PURCHASING', 'INVENTORY', 'SALES')")
    public ResponseEntity<ApiResponse<ProductStockResponse>> getStock(@PathVariable Long id) {
        ProductStockResponse response = productService.getStock(id);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Stock obtenido exitosamente",
                response
        ));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'PURCHASING')")
    public ResponseEntity<ApiResponse<ProductResponse>> create(
            @Valid @RequestBody ProductRequest request
    ) {
        ProductResponse response = productService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(
                HttpStatus.CREATED.value(),
                "Producto creado exitosamente",
                response
        ));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'PURCHASING')")
    public ResponseEntity<ApiResponse<ProductResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request
    ) {
        ProductResponse response = productService.update(id, request);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Producto actualizado exitosamente",
                response
        ));
    }

    @PatchMapping("/{id}/sale-price")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'INVENTORY')")
    public ResponseEntity<ApiResponse<ProductResponse>> changeSalePrice(
            @PathVariable Long id,
            @Valid @RequestBody ChangeSalePriceRequest request
    ) {
        ProductResponse response = productService.changeSalePrice(id, request);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Precio de venta actualizado exitosamente",
                response
        ));
    }

    @PatchMapping("/{id}/min-stock")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'INVENTORY')")
    public ResponseEntity<ApiResponse<ProductResponse>> changeMinStock(
            @PathVariable Long id,
            @Valid @RequestBody ChangeMinStockRequest request
    ) {
        ProductResponse response = productService.changeMinStock(id, request);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Stock mínimo actualizado exitosamente",
                response
        ));
    }

    @PatchMapping("/{id}/category")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'PURCHASING')")
    public ResponseEntity<ApiResponse<ProductResponse>> changeCategory(
            @PathVariable Long id,
            @Valid @RequestBody ChangeCategoryRequest request
    ) {
        ProductResponse response = productService.changeCategory(id, request);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Categoría del producto actualizada exitosamente",
                response
        ));
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMINISTRATION')")
    public ResponseEntity<ApiResponse<ProductResponse>> updateStatus(
            @PathVariable Long id,
            @RequestParam boolean active
    ) {
        ProductResponse response = productService.updateStatus(id, active);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Estado del producto actualizado exitosamente",
                response
        ));
    }

    @PostMapping("/{id}/stock/adjustment")
    @PreAuthorize("hasRole('INVENTORY')")
    public ResponseEntity<ApiResponse<ProductResponse>> adjustStock(
            @PathVariable Long id,
            @Valid @RequestBody ManualStockAdjustmentRequest request
    ) {
        ProductResponse response = productService.adjustStock(id, request);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Stock modificado manualmente exitosamente",
                response
        ));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATION')")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Producto desactivado exitosamente",
                null
        ));
    }
}
