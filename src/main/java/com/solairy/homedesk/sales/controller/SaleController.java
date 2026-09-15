package com.solairy.homedesk.sales.controller;

import com.solairy.homedesk.common.response.ApiResponse;
import com.solairy.homedesk.common.response.PageResponse;
import com.solairy.homedesk.sales.dto.request.SaleRequest;
import com.solairy.homedesk.sales.dto.request.SaleStatusChangeRequest;
import com.solairy.homedesk.sales.dto.response.SaleResponse;
import com.solairy.homedesk.sales.service.SaleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sales")
@RequiredArgsConstructor
@Tag(name = "Sales", description = "Endpoints para la gestión de ventas")
public class SaleController {

    private final SaleService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('SALES', 'ADMINISTRATION')")
    public ResponseEntity<ApiResponse<PageResponse<SaleResponse>>> findAll(
            @ParameterObject
            @PageableDefault(size = 10, sort = "saleDate", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PageResponse<SaleResponse> response = service.findAll(pageable);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Ventas obtenidas exitosamente",
                response
        ));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('SALES', 'ADMINISTRATION')")
    public ResponseEntity<ApiResponse<SaleResponse>> findById(@PathVariable Long id) {
        SaleResponse response = service.findById(id);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Venta obtenida exitosamente",
                response
        ));
    }

    @GetMapping("/invoice/{invoiceNumber}")
    @PreAuthorize("hasAnyRole('SALES', 'ADMINISTRATION')")
    public ResponseEntity<ApiResponse<SaleResponse>> findByInvoiceNumber(@PathVariable String invoiceNumber) {
        SaleResponse response = service.findByInvoiceNumber(invoiceNumber);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Venta obtenida exitosamente por número de factura",
                response
        ));
    }

    @GetMapping("/customer/{customerId}")
    @PreAuthorize("hasAnyRole('SALES', 'ADMINISTRATION')")
    public ResponseEntity<ApiResponse<PageResponse<SaleResponse>>> findByCustomer(
            @PathVariable Long customerId,
            @ParameterObject
            @PageableDefault(size = 10, sort = "saleDate", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PageResponse<SaleResponse> response = service.findByCustomer(customerId, pageable);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Ventas del cliente obtenidas exitosamente",
                response
        ));
    }

    @GetMapping("/status/{statusId}")
    @PreAuthorize("hasAnyRole('SALES', 'ADMINISTRATION')")
    public ResponseEntity<ApiResponse<PageResponse<SaleResponse>>> findByStatus(
            @PathVariable Long statusId,
            @ParameterObject
            @PageableDefault(size = 10, sort = "saleDate", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PageResponse<SaleResponse> response = service.findByStatus(statusId, pageable);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Ventas filtradas por estado obtenidas exitosamente",
                response
        ));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('SALES', 'ADMINISTRATION')")
    public ResponseEntity<ApiResponse<SaleResponse>> create(
            @Valid @RequestBody SaleRequest request
    ) {
        SaleResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(
                HttpStatus.CREATED.value(),
                "Venta registrada exitosamente",
                response
        ));
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('SALES', 'ADMINISTRATION')")
    public ResponseEntity<ApiResponse<SaleResponse>> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody SaleStatusChangeRequest request
    ) {
        SaleResponse response = service.updateStatus(id, request);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Estado de la venta actualizado exitosamente",
                response
        ));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATION')")
    public ResponseEntity<ApiResponse<Void>> cancel(@PathVariable Long id) {
        service.cancel(id);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Venta cancelada exitosamente",
                null
        ));
    }
}
