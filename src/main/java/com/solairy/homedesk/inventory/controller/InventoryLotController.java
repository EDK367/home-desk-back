package com.solairy.homedesk.inventory.controller;

import com.solairy.homedesk.common.response.ApiResponse;
import com.solairy.homedesk.common.response.PageResponse;
import com.solairy.homedesk.inventory.dto.response.InventoryLotResponse;
import com.solairy.homedesk.inventory.service.InventoryLotService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory-lots")
@RequiredArgsConstructor
@Tag(name = "Inventory Lots", description = "Endpoints para la gestión y consulta de lotes de inventario")
public class InventoryLotController {

    private final InventoryLotService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'INVENTORY')")
    public ResponseEntity<ApiResponse<PageResponse<InventoryLotResponse>>> findAll(
            @ParameterObject
            @PageableDefault(size = 10, sort = "receivedAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PageResponse<InventoryLotResponse> response = service.findAll(pageable);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Lotes de inventario obtenidos exitosamente",
                response
        ));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'INVENTORY')")
    public ResponseEntity<ApiResponse<InventoryLotResponse>> findById(@PathVariable Long id) {
        InventoryLotResponse response = service.findById(id);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Lote de inventario obtenido exitosamente",
                response
        ));
    }

    @GetMapping("/product/{productId}")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'INVENTORY')")
    public ResponseEntity<ApiResponse<PageResponse<InventoryLotResponse>>> findByProduct(
            @PathVariable Long productId,
            @ParameterObject
            @PageableDefault(size = 10, sort = "receivedAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PageResponse<InventoryLotResponse> response = service.findByProduct(productId, pageable);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Lotes del producto obtenidos exitosamente",
                response
        ));
    }

    @GetMapping("/product/{productId}/active")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'INVENTORY')")
    public ResponseEntity<ApiResponse<List<InventoryLotResponse>>> findActiveLotsByProduct(@PathVariable Long productId) {
        List<InventoryLotResponse> response = service.findActiveLotsByProduct(productId);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Lotes activos del producto obtenidos exitosamente",
                response
        ));
    }
}
