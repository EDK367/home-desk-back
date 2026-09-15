package com.solairy.homedesk.inventory.controller;

import com.solairy.homedesk.common.response.ApiResponse;
import com.solairy.homedesk.common.response.PageResponse;
import com.solairy.homedesk.inventory.dto.request.ManualStockAdjustmentRequest;
import com.solairy.homedesk.inventory.dto.response.InventoryResponse;
import com.solairy.homedesk.inventory.service.InventoryService;
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
@RequestMapping("/api/v1/inventories")
@RequiredArgsConstructor
@Tag(name = "Inventories", description = "Endpoints para la gestión y consulta de inventario")
public class InventoryController {

    private final InventoryService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'INVENTORY', 'PURCHASING', 'SALES')")
    public ResponseEntity<ApiResponse<PageResponse<InventoryResponse>>> findAll(
            @ParameterObject
            @PageableDefault(size = 10, sort = "updatedAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PageResponse<InventoryResponse> response = service.findAll(pageable);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Inventario obtenido exitosamente",
                response
        ));
    }

    @GetMapping("/low-stock")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'INVENTORY', 'PURCHASING')")
    public ResponseEntity<ApiResponse<PageResponse<InventoryResponse>>> findLowStock(
            @ParameterObject
            @PageableDefault(size = 10, sort = "quantityOnHand", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        PageResponse<InventoryResponse> response = service.findLowStock(pageable);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Productos con existencias bajas obtenidos exitosamente",
                response
        ));
    }

    @GetMapping("/category/{categoryId}")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'INVENTORY', 'PURCHASING', 'SALES')")
    public ResponseEntity<ApiResponse<PageResponse<InventoryResponse>>> findByCategory(
            @PathVariable Long categoryId,
            @ParameterObject
            @PageableDefault(size = 10, sort = "updatedAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PageResponse<InventoryResponse> response = service.findByCategory(categoryId, pageable);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Inventario por categoría obtenido exitosamente",
                response
        ));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'INVENTORY', 'PURCHASING', 'SALES')")
    public ResponseEntity<ApiResponse<InventoryResponse>> findById(@PathVariable Long id) {
        InventoryResponse response = service.findById(id);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Registro de inventario obtenido exitosamente",
                response
        ));
    }

    @GetMapping("/product/{productId}")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'INVENTORY', 'PURCHASING', 'SALES')")
    public ResponseEntity<ApiResponse<InventoryResponse>> findByProductId(@PathVariable Long productId) {
        InventoryResponse response = service.findByProductId(productId);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Inventario del producto obtenido exitosamente",
                response
        ));
    }

    @PostMapping("/product/{productId}/adjust")
    @PreAuthorize("hasRole('INVENTORY')")
    public ResponseEntity<ApiResponse<InventoryResponse>> adjustStock(
            @PathVariable Long productId,
            @Valid @RequestBody ManualStockAdjustmentRequest request
    ) {
        InventoryResponse response = service.adjustStock(productId, request);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Stock modificado manualmente exitosamente",
                response
        ));
    }
}
