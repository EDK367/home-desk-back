package com.solairy.homedesk.sales.service.impl;

import com.solairy.homedesk.common.exception.GlobalException;
import com.solairy.homedesk.sales.dto.response.SaleStatusResponse;
import com.solairy.homedesk.sales.mapper.SaleStatusMapper;
import com.solairy.homedesk.sales.model.SaleStatus;
import com.solairy.homedesk.sales.repository.SaleStatusRepository;
import com.solairy.homedesk.sales.service.SaleStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleStatusServiceImpl implements SaleStatusService {

    private final SaleStatusRepository repository;
    private final SaleStatusMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<SaleStatusResponse> findAll() {
        return repository.findAllByIsActive(true)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SaleStatusResponse findById(Long id) {
        SaleStatus status = repository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Estado de venta no encontrado con id: " + id));
        return mapper.toResponse(status);
    }
}
