package com.solairy.homedesk.suppliers.service.impl;

import com.solairy.homedesk.suppliers.repository.PhoneSupplierRepository;
import com.solairy.homedesk.suppliers.service.PhoneSupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhoneSupplierServiceImpl implements PhoneSupplierService {

    private final PhoneSupplierRepository phoneSupplierRepository;

}
