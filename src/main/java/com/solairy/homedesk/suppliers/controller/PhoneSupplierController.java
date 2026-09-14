package com.solairy.homedesk.suppliers.controller;

import com.solairy.homedesk.suppliers.service.PhoneSupplierService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/phone-suppliers")
@RequiredArgsConstructor
@Tag(name = "Phone Suppliers", description = "Endpoints for phone supplier management")
public class PhoneSupplierController {

    private final PhoneSupplierService phoneSupplierService;

}
