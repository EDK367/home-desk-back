package com.solairy.homedesk.payment.controller;

import com.solairy.homedesk.common.response.ApiResponse;
import com.solairy.homedesk.common.response.PageResponse;
import com.solairy.homedesk.payment.dto.request.PaymentMethodRequest;
import com.solairy.homedesk.payment.dto.response.PaymentMethodResponse;
import com.solairy.homedesk.payment.service.PaymentMethodService;
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
@RequestMapping("/api/v1/payment-methods")
@RequiredArgsConstructor
@Tag(name = "Payment Methods", description = "Endpoints para la gestión de métodos de pago")
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    @GetMapping({"", "/sales"})
    @PreAuthorize("hasAnyRole('SALES', 'ADMINISTRATION')")
    public ResponseEntity<ApiResponse<PageResponse<PaymentMethodResponse>>> findAllSales(
            @ParameterObject
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PageResponse<PaymentMethodResponse> response = paymentMethodService.findAllSales(pageable);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Métodos de pago activos obtenidos exitosamente",
                response
        ));
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMINISTRATION')")
    public ResponseEntity<ApiResponse<PageResponse<PaymentMethodResponse>>> findAllAdmin(
            @ParameterObject
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PageResponse<PaymentMethodResponse> response = paymentMethodService.findAllAdmin(pageable);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Métodos de pago obtenidos exitosamente",
                response
        ));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('SALES', 'ADMINISTRATION')")
    public ResponseEntity<ApiResponse<PaymentMethodResponse>> findById(@PathVariable Long id) {
        PaymentMethodResponse response = paymentMethodService.findById(id);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Método de pago obtenido exitosamente",
                response
        ));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRATION')")
    public ResponseEntity<ApiResponse<PaymentMethodResponse>> create(
            @Valid @RequestBody PaymentMethodRequest request
    ) {
        PaymentMethodResponse response = paymentMethodService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(
                HttpStatus.CREATED.value(),
                "Método de pago creado exitosamente",
                response
        ));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATION')")
    public ResponseEntity<ApiResponse<PaymentMethodResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody PaymentMethodRequest request
    ) {
        PaymentMethodResponse response = paymentMethodService.update(id, request);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Método de pago actualizado exitosamente",
                response
        ));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATION')")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        paymentMethodService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Método de pago desactivado exitosamente",
                null
        ));
    }
}
