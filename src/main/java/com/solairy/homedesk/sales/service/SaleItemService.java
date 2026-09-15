package com.solairy.homedesk.sales.service;

import com.solairy.homedesk.sales.dto.response.SaleItemResponse;

import java.util.List;

public interface SaleItemService {

    List<SaleItemResponse> findBySaleId(Long saleId);

    SaleItemResponse findById(Long id);
}
