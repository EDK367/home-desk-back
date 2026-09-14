package com.solairy.homedesk.suppliers.controller;

import com.solairy.homedesk.common.response.ApiResponse;
import com.solairy.homedesk.common.response.PageResponse;
import com.solairy.homedesk.suppliers.dto.request.SupplierRequest;
import com.solairy.homedesk.suppliers.dto.response.SupplierResponse;
import com.solairy.homedesk.suppliers.service.SupplierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/suppliers")
@RequiredArgsConstructor
@Tag(name = "Suppliers", description = "Endpoints para los proveedores")
public class SupplierController {

    private final SupplierService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'PURCHASING')")
    public ResponseEntity<ApiResponse<PageResponse<SupplierResponse>>> findAll(
            @ParameterObject
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(defaultValue = "false") boolean includeInactive
    ) {
        PageResponse<SupplierResponse> response = service.findAll(pageable, includeInactive);

        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Provedores obtenidos exitosamente",
                response
        ));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRATION', 'PURCHASING')")
    public ResponseEntity<ApiResponse<SupplierResponse>> findById(@PathVariable Long id) {
        SupplierResponse response = service.findById(id);
        return ResponseEntity
                .ok(ApiResponse.success(HttpStatus.OK.value(), "Proveedor obtenido correctamente", response));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRATION')")
    public ResponseEntity<ApiResponse<SupplierResponse>> create(@Valid @RequestBody SupplierRequest request) {
        SupplierResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(HttpStatus.CREATED.value(), "Proveedor creado correctamente", response));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATION')")
    public ResponseEntity<ApiResponse<SupplierResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody SupplierRequest request) {
        SupplierResponse response = service.update(id, request);
        return ResponseEntity
                .ok(ApiResponse.success(HttpStatus.OK.value(), "Proveedor actualizado correctamente", response));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATION')")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity
                .ok(ApiResponse.success(HttpStatus.OK.value(), "Proveedor desactivado correctamente", null));
    }
}
