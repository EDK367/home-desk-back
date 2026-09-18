package com.solairy.homedesk.reports.controller;

import com.solairy.homedesk.common.response.ApiResponse;
import com.solairy.homedesk.common.response.PageResponse;
import com.solairy.homedesk.reports.service.ReportService;
import com.solairy.homedesk.reports.views.VwProductsLowestStock;
import com.solairy.homedesk.reports.views.VwProductsMostFrequentlyPurchased;
import com.solairy.homedesk.reports.views.VwProductsMostInventoryMovement;
import com.solairy.homedesk.reports.views.VwTop10CustomersBySale;
import com.solairy.homedesk.reports.views.VwTop10ProductsByRevenue;
import com.solairy.homedesk.reports.views.VwTop10ProductsSold;
import com.solairy.homedesk.reports.views.VwTop5SuppliersByPurchase;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
@Tag(name = "Reports", description = "Endpoints para la consulta y generación de reportes")
public class ReportController {

    private final ReportService service;

    @GetMapping("/top-products-sold")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'PURCHASING')")
    public ResponseEntity<ApiResponse<List<VwTop10ProductsSold>>> findTop10ProductsSold() {
        List<VwTop10ProductsSold> response = service.findTop10ProductsSold();
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Top 10 productos más vendidos obtenidos exitosamente",
                response
        ));
    }

    @GetMapping("/top-products-revenue")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'PURCHASING')")
    public ResponseEntity<ApiResponse<List<VwTop10ProductsByRevenue>>> findTop10ProductsByRevenue() {
        List<VwTop10ProductsByRevenue> response = service.findTop10ProductsByRevenue();
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Top 10 productos con mayor ingreso obtenidos exitosamente",
                response
        ));
    }

    @GetMapping("/top-customers-sales")
    @PreAuthorize("hasAnyRole('ADMINISTRATION')")
    public ResponseEntity<ApiResponse<List<VwTop10CustomersBySale>>> findTop10CustomersBySale() {
        List<VwTop10CustomersBySale> response = service.findTop10CustomersBySale();
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Top 10 clientes con mayor monto acumulado de compras obtenidos exitosamente",
                response
        ));
    }

    @GetMapping("/top-suppliers-purchases")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'PURCHASING')")
    public ResponseEntity<ApiResponse<List<VwTop5SuppliersByPurchase>>> findTop5SuppliersByPurchase() {
        List<VwTop5SuppliersByPurchase> response = service.findTop5SuppliersByPurchase();
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Top 5 proveedores con mayor monto acumulado de compras obtenidos exitosamente",
                response
        ));
    }

    @GetMapping("/products-lowest-stock")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'INVENTORY', 'PURCHASING')")
    public ResponseEntity<ApiResponse<PageResponse<VwProductsLowestStock>>> findProductsLowestStock(
            @ParameterObject
            @PageableDefault(size = 10, sort = "quantityOnHand", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        PageResponse<VwProductsLowestStock> response = service.findProductsLowestStock(pageable);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Productos con menor existencia disponible obtenidos exitosamente",
                response
        ));
    }

    @GetMapping("/products-most-frequently-purchased")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'PURCHASING')")
    public ResponseEntity<ApiResponse<PageResponse<VwProductsMostFrequentlyPurchased>>> findProductsMostFrequentlyPurchased(
            @ParameterObject
            @PageableDefault(size = 10, sort = "purchaseCount", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PageResponse<VwProductsMostFrequentlyPurchased> response = service.findProductsMostFrequentlyPurchased(pageable);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Productos adquiridos con mayor frecuencia obtenidos exitosamente",
                response
        ));
    }

    @GetMapping("/products-most-inventory-movements")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'INVENTORY')")
    public ResponseEntity<ApiResponse<PageResponse<VwProductsMostInventoryMovement>>> findProductsMostInventoryMovements(
            @ParameterObject
            @PageableDefault(size = 10, sort = "movementCount", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PageResponse<VwProductsMostInventoryMovement> response = service.findProductsMostInventoryMovements(pageable);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Productos con mayor cantidad de movimientos obtenidos exitosamente",
                response
        ));
    }
}
