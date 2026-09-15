package com.solairy.homedesk.sales.service.impl;

import com.solairy.homedesk.common.exception.GlobalException;
import com.solairy.homedesk.sales.dto.response.SaleItemResponse;
import com.solairy.homedesk.sales.mapper.SaleItemMapper;
import com.solairy.homedesk.sales.model.SaleItem;
import com.solairy.homedesk.sales.repository.SaleItemRepository;
import com.solairy.homedesk.sales.service.SaleItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleItemServiceImpl implements SaleItemService {

    private final SaleItemRepository repository;
    private final SaleItemMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<SaleItemResponse> findBySaleId(Long saleId) {
        return repository.findBySaleId(saleId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SaleItemResponse findById(Long id) {
        SaleItem item = repository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Ítem de venta no encontrado con id: " + id));
        return mapper.toResponse(item);
    }
}
