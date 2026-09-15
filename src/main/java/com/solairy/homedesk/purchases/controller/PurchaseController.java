package com.solairy.homedesk.purchases.controller;

import com.solairy.homedesk.common.response.ApiResponse;
import com.solairy.homedesk.common.response.PageResponse;
import com.solairy.homedesk.purchases.dto.request.PurchaseRequest;
import com.solairy.homedesk.purchases.dto.request.PurchaseStatusChangeRequest;
import com.solairy.homedesk.purchases.dto.response.PurchaseResponse;
import com.solairy.homedesk.purchases.service.PurchaseService;
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
@RequestMapping("/api/v1/purchases")
@RequiredArgsConstructor
@Tag(name = "Purchases", description = "Endpoints para la gestión de compras")
public class PurchaseController {

    private final PurchaseService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'PURCHASING')")
    public ResponseEntity<ApiResponse<PageResponse<PurchaseResponse>>> findAll(
            @ParameterObject
            @PageableDefault(size = 10, sort = "purchaseDate", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PageResponse<PurchaseResponse> response = service.findAll(pageable);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Compras obtenidas exitosamente",
                response
        ));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'PURCHASING')")
    public ResponseEntity<ApiResponse<PurchaseResponse>> findById(@PathVariable Long id) {
        PurchaseResponse response = service.findById(id);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Compra obtenida exitosamente",
                response
        ));
    }

    @GetMapping("/supplier/{supplierId}")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'PURCHASING')")
    public ResponseEntity<ApiResponse<PageResponse<PurchaseResponse>>> findBySupplier(
            @PathVariable Long supplierId,
            @ParameterObject
            @PageableDefault(size = 10, sort = "purchaseDate", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PageResponse<PurchaseResponse> response = service.findBySupplier(supplierId, pageable);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Compras del proveedor obtenidas exitosamente",
                response
        ));
    }

    @GetMapping("/status/{statusId}")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'PURCHASING')")
    public ResponseEntity<ApiResponse<PageResponse<PurchaseResponse>>> findByStatus(
            @PathVariable Long statusId,
            @ParameterObject
            @PageableDefault(size = 10, sort = "purchaseDate", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PageResponse<PurchaseResponse> response = service.findByStatus(statusId, pageable);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Compras filtradas por estado obtenidas exitosamente",
                response
        ));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'PURCHASING')")
    public ResponseEntity<ApiResponse<PurchaseResponse>> create(
            @Valid @RequestBody PurchaseRequest request
    ) {
        PurchaseResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(
                HttpStatus.CREATED.value(),
                "Compra registrada exitosamente",
                response
        ));
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'PURCHASING')")
    public ResponseEntity<ApiResponse<PurchaseResponse>> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody PurchaseStatusChangeRequest request
    ) {
        PurchaseResponse response = service.updateStatus(id, request);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Estado de la compra actualizado exitosamente",
                response
        ));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'PURCHASING')")
    public ResponseEntity<ApiResponse<Void>> cancel(@PathVariable Long id) {
        service.cancel(id);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Compra cancelada exitosamente",
                null
        ));
    }
}
