package com.solairy.homedesk.suppliers.service.impl;

import com.solairy.homedesk.suppliers.repository.PhoneNumberRepository;
import com.solairy.homedesk.suppliers.service.PhoneNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhoneNumberServiceImpl implements PhoneNumberService {

    private final PhoneNumberRepository phoneNumberRepository;

}
