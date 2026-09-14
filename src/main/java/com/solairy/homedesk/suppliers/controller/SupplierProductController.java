package com.solairy.homedesk.suppliers.controller;

import com.solairy.homedesk.suppliers.service.SupplierProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/supplier-products")
@RequiredArgsConstructor
@Tag(name = "Supplier Products", description = "Endpoints for supplier product management")
public class SupplierProductController {

    private final SupplierProductService supplierProductService;

}
