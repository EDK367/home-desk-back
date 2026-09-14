package com.solairy.homedesk.catalog.service.impl;

import com.solairy.homedesk.catalog.repository.ProductRepository;
import com.solairy.homedesk.catalog.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

}
