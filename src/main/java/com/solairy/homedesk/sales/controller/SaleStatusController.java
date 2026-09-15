package com.solairy.homedesk.sales.controller;

import com.solairy.homedesk.common.response.ApiResponse;
import com.solairy.homedesk.sales.dto.response.SaleStatusResponse;
import com.solairy.homedesk.sales.service.SaleStatusService;
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
@RequestMapping("/api/v1/sale-statuses")
@RequiredArgsConstructor
@Tag(name = "Sale Statuses", description = "Endpoints para la gestión de estados de venta")
public class SaleStatusController {

    private final SaleStatusService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('SALES', 'ADMINISTRATION')")
    public ResponseEntity<ApiResponse<List<SaleStatusResponse>>> findAll() {
        List<SaleStatusResponse> response = service.findAll();
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Estados de venta obtenidos exitosamente",
                response
        ));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('SALES', 'ADMINISTRATION')")
    public ResponseEntity<ApiResponse<SaleStatusResponse>> findById(@PathVariable Long id) {
        SaleStatusResponse response = service.findById(id);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Estado de venta obtenido exitosamente",
                response
        ));
    }
}
