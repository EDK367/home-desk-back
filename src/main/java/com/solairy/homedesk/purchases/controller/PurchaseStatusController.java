package com.solairy.homedesk.purchases.controller;

import com.solairy.homedesk.common.response.ApiResponse;
import com.solairy.homedesk.purchases.dto.response.PurchaseStatusResponse;
import com.solairy.homedesk.purchases.service.PurchaseStatusService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/purchase-statuses")
@RequiredArgsConstructor
@Tag(name = "Purchase Statuses", description = "Endpoints para la gestión de estados de compra")
public class PurchaseStatusController {

    private final PurchaseStatusService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'PURCHASING')")
    public ResponseEntity<ApiResponse<List<PurchaseStatusResponse>>> findAll() {
        List<PurchaseStatusResponse> response = service.findAll();
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Estados de compra obtenidos exitosamente",
                response
        ));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'PURCHASING')")
    public ResponseEntity<ApiResponse<PurchaseStatusResponse>> findById(@PathVariable Long id) {
        PurchaseStatusResponse response = service.findById(id);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Estado de compra obtenido exitosamente",
                response
        ));
    }
}
