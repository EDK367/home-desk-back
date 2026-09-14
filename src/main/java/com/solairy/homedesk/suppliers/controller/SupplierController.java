package com.solairy.homedesk.suppliers.controller;

import com.solairy.homedesk.suppliers.service.SupplierService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/suppliers")
@RequiredArgsConstructor
@Tag(name = "Suppliers", description = "Endpoints for supplier management")
public class SupplierController {

    private final SupplierService supplierService;

}
