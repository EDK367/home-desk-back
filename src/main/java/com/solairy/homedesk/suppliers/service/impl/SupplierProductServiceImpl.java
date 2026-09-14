package com.solairy.homedesk.suppliers.service.impl;

import com.solairy.homedesk.suppliers.repository.SupplierProductRepository;
import com.solairy.homedesk.suppliers.service.SupplierProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierProductServiceImpl implements SupplierProductService {

    private final SupplierProductRepository supplierProductRepository;

}
