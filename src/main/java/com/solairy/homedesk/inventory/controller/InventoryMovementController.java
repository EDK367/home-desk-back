package com.solairy.homedesk.inventory.controller;

import com.solairy.homedesk.common.response.ApiResponse;
import com.solairy.homedesk.common.response.PageResponse;
import com.solairy.homedesk.inventory.dto.response.InventoryMovementResponse;
import com.solairy.homedesk.inventory.service.InventoryMovementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/api/v1/inventory-movements")
@RequiredArgsConstructor
@Tag(name = "Inventory Movements", description = "Endpoints para el Kardex y movimientos de inventario")
public class InventoryMovementController {

    private final InventoryMovementService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'INVENTORY')")
    public ResponseEntity<ApiResponse<PageResponse<InventoryMovementResponse>>> findAll(
            @ParameterObject
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime endDate,
            @RequestParam(required = false) String movementType
    ) {
        PageResponse<InventoryMovementResponse> response;
        if (movementType != null && !movementType.isBlank()) {
            response = service.findByType(movementType.trim(), pageable);
        } else if (startDate != null && endDate != null) {
            response = service.findByDateRange(startDate, endDate, pageable);
        } else {
            response = service.findAll(pageable);
        }

        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Movimientos de inventario obtenidos exitosamente",
                response
        ));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'INVENTORY')")
    public ResponseEntity<ApiResponse<InventoryMovementResponse>> findById(@PathVariable Long id) {
        InventoryMovementResponse response = service.findById(id);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Movimiento de inventario obtenido exitosamente",
                response
        ));
    }

    @GetMapping("/product/{productId}")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'INVENTORY')")
    public ResponseEntity<ApiResponse<PageResponse<InventoryMovementResponse>>> findByProduct(
            @PathVariable Long productId,
            @ParameterObject
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime endDate
    ) {
        PageResponse<InventoryMovementResponse> response;
        if (startDate != null && endDate != null) {
            response = service.findByProductAndDateRange(productId, startDate, endDate, pageable);
        } else {
            response = service.findByProduct(productId, pageable);
        }

        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Kardex del producto obtenido exitosamente",
                response
        ));
    }

    @GetMapping("/type/{movementType}")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'INVENTORY')")
    public ResponseEntity<ApiResponse<PageResponse<InventoryMovementResponse>>> findByType(
            @PathVariable String movementType,
            @ParameterObject
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PageResponse<InventoryMovementResponse> response = service.findByType(movementType, pageable);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Movimientos por tipo obtenidos exitosamente",
                response
        ));
    }
}
