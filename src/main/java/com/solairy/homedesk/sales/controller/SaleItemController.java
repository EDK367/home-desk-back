package com.solairy.homedesk.sales.controller;

import com.solairy.homedesk.common.response.ApiResponse;
import com.solairy.homedesk.sales.dto.response.SaleItemResponse;
import com.solairy.homedesk.sales.service.SaleItemService;
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
@RequestMapping("/api/v1/sale-items")
@RequiredArgsConstructor
@Tag(name = "Sale Items", description = "Endpoints para la gestión de ítems de venta")
public class SaleItemController {

    private final SaleItemService service;

    @GetMapping("/sale/{saleId}")
    @PreAuthorize("hasAnyRole('SALES', 'ADMINISTRATION')")
    public ResponseEntity<ApiResponse<List<SaleItemResponse>>> findBySaleId(@PathVariable Long saleId) {
        List<SaleItemResponse> response = service.findBySaleId(saleId);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Ítems de venta obtenidos exitosamente",
                response
        ));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('SALES', 'ADMINISTRATION')")
    public ResponseEntity<ApiResponse<SaleItemResponse>> findById(@PathVariable Long id) {
        SaleItemResponse response = service.findById(id);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Ítem de venta obtenido exitosamente",
                response
        ));
    }
}
