package com.solairy.homedesk.inventory.controller;

import com.solairy.homedesk.common.response.ApiResponse;
import com.solairy.homedesk.inventory.dto.response.InventoryMovementLotResponse;
import com.solairy.homedesk.inventory.service.InventoryMovementLotService;
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
@RequestMapping("/api/v1/inventory-movement-lots")
@RequiredArgsConstructor
@Tag(name = "Inventory Movement Lots", description = "Endpoints para los lotes afectados en movimientos de inventario")
public class InventoryMovementLotController {

    private final InventoryMovementLotService service;

    @GetMapping("/movement/{movementId}")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'INVENTORY')")
    public ResponseEntity<ApiResponse<List<InventoryMovementLotResponse>>> findByMovementId(@PathVariable Long movementId) {
        List<InventoryMovementLotResponse> response = service.findByMovementId(movementId);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Detalles de lotes del movimiento obtenidos exitosamente",
                response
        ));
    }

    @GetMapping("/lot/{lotId}")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'INVENTORY')")
    public ResponseEntity<ApiResponse<List<InventoryMovementLotResponse>>> findByLotId(@PathVariable Long lotId) {
        List<InventoryMovementLotResponse> response = service.findByLotId(lotId);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Movimientos asociados al lote obtenidos exitosamente",
                response
        ));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'INVENTORY')")
    public ResponseEntity<ApiResponse<InventoryMovementLotResponse>> findById(@PathVariable Long id) {
        InventoryMovementLotResponse response = service.findById(id);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Detalle de lote de movimiento obtenido exitosamente",
                response
        ));
    }
}
