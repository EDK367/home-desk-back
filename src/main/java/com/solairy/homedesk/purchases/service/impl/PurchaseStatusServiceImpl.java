package com.solairy.homedesk.purchases.service.impl;

import com.solairy.homedesk.common.exception.GlobalException;
import com.solairy.homedesk.purchases.dto.response.PurchaseStatusResponse;
import com.solairy.homedesk.purchases.mapper.PurchaseStatusMapper;
import com.solairy.homedesk.purchases.model.PurchaseStatus;
import com.solairy.homedesk.purchases.repository.PurchaseStatusRepository;
import com.solairy.homedesk.purchases.service.PurchaseStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseStatusServiceImpl implements PurchaseStatusService {

    private final PurchaseStatusRepository repository;
    private final PurchaseStatusMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<PurchaseStatusResponse> findAll() {
        return repository.findAllByIsActive(true)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PurchaseStatusResponse findById(Long id) {
        PurchaseStatus status = repository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Estado de compra no encontrado con id: " + id));
        return mapper.toResponse(status);
    }
}
