package com.solairy.homedesk.suppliers.service.impl;

import com.solairy.homedesk.suppliers.repository.SupplierRepository;
import com.solairy.homedesk.suppliers.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

}
