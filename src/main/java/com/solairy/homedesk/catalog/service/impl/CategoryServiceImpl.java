package com.solairy.homedesk.catalog.service.impl;

import com.solairy.homedesk.catalog.repository.CategoryRepository;
import com.solairy.homedesk.catalog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

}
