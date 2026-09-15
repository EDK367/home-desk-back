package com.solairy.homedesk.purchases.controller;

import com.solairy.homedesk.common.response.ApiResponse;
import com.solairy.homedesk.purchases.dto.response.PurchaseItemResponse;
import com.solairy.homedesk.purchases.service.PurchaseItemService;
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
@RequestMapping("/api/v1/purchase-items")
@RequiredArgsConstructor
@Tag(name = "Purchase Items", description = "Endpoints para la gestión de ítems de compra")
public class PurchaseItemController {

    private final PurchaseItemService service;

    @GetMapping("/purchase/{purchaseId}")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'PURCHASING')")
    public ResponseEntity<ApiResponse<List<PurchaseItemResponse>>> findByPurchaseId(@PathVariable Long purchaseId) {
        List<PurchaseItemResponse> response = service.findByPurchaseId(purchaseId);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Ítems de compra obtenidos exitosamente",
                response
        ));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'PURCHASING')")
    public ResponseEntity<ApiResponse<PurchaseItemResponse>> findById(@PathVariable Long id) {
        PurchaseItemResponse response = service.findById(id);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Ítem de compra obtenido exitosamente",
                response
        ));
    }
}
