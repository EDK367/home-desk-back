package com.solairy.homedesk.suppliers.controller;

import com.solairy.homedesk.suppliers.service.PhoneNumberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/phone-numbers")
@RequiredArgsConstructor
@Tag(name = "Phone Numbers", description = "Endpoints for phone number management")
public class PhoneNumberController {

    private final PhoneNumberService phoneNumberService;

}
