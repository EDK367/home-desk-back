package com.solairy.homedesk.payment.controller;

import com.solairy.homedesk.common.response.ApiResponse;
import com.solairy.homedesk.common.response.PageResponse;
import com.solairy.homedesk.payment.dto.request.SalePaymentRequest;
import com.solairy.homedesk.payment.dto.response.SalePaymentResponse;
import com.solairy.homedesk.payment.service.SalePaymentService;
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
@RequestMapping("/api/v1/sale-payments")
@RequiredArgsConstructor
@Tag(name = "Sale Payments", description = "Endpoints para la gestión de pagos de venta")
public class SalePaymentController {

    private final SalePaymentService salePaymentService;

    @GetMapping("/sale/{saleId}")
    @PreAuthorize("hasAnyRole('SALES', 'ADMINISTRATION')")
    public ResponseEntity<ApiResponse<PageResponse<SalePaymentResponse>>> findBySale(
            @PathVariable Long saleId,
            @ParameterObject
            @PageableDefault(size = 10, sort = "paymentDate", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PageResponse<SalePaymentResponse> response = salePaymentService.findBySale(saleId, pageable);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Pagos de la venta obtenidos exitosamente",
                response
        ));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('SALES', 'ADMINISTRATION')")
    public ResponseEntity<ApiResponse<SalePaymentResponse>> findById(@PathVariable Long id) {
        SalePaymentResponse response = salePaymentService.findById(id);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Pago obtenido exitosamente",
                response
        ));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('SALES', 'ADMINISTRATION')")
    public ResponseEntity<ApiResponse<SalePaymentResponse>> create(
            @Valid @RequestBody SalePaymentRequest request
    ) {
        SalePaymentResponse response = salePaymentService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(
                HttpStatus.CREATED.value(),
                "Pago registrado exitosamente",
                response
        ));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATION')")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        salePaymentService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Pago eliminado exitosamente",
                null
        ));
    }
}
