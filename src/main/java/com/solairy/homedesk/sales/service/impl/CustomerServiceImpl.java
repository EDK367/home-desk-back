package com.solairy.homedesk.sales.service.impl;

import com.solairy.homedesk.sales.repository.CustomerRepository;
import com.solairy.homedesk.sales.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

}
