package com.solairy.homedesk.sales.service;

import com.solairy.homedesk.sales.dto.response.SaleStatusResponse;

import java.util.List;

public interface SaleStatusService {

    List<SaleStatusResponse> findAll();

    SaleStatusResponse findById(Long id);
}
