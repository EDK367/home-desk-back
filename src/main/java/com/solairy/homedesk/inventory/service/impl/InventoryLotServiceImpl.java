package com.solairy.homedesk.inventory.service.impl;

import com.solairy.homedesk.catalog.repository.ProductRepository;
import com.solairy.homedesk.common.exception.GlobalException;
import com.solairy.homedesk.common.response.PageResponse;
import com.solairy.homedesk.inventory.dto.response.InventoryLotResponse;
import com.solairy.homedesk.inventory.mapper.InventoryLotMapper;
import com.solairy.homedesk.inventory.model.InventoryLot;
import com.solairy.homedesk.inventory.repository.InventoryLotRepository;
import com.solairy.homedesk.inventory.service.InventoryLotService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryLotServiceImpl implements InventoryLotService {

    private final InventoryLotRepository repository;
    private final ProductRepository productRepository;
    private final InventoryLotMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public PageResponse<InventoryLotResponse> findAll(Pageable pageable) {
        Page<InventoryLot> page = repository.findAll(pageable);
        return PageResponse.from(page, mapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<InventoryLotResponse> findByProduct(Long productId, Pageable pageable) {
        if (!productRepository.existsById(productId)) {
            throw new GlobalException(HttpStatus.NOT_FOUND, "Producto no encontrado con id: " + productId);
        }
        Page<InventoryLot> page = repository.findByProductId(productId, pageable);
        return PageResponse.from(page, mapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InventoryLotResponse> findActiveLotsByProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new GlobalException(HttpStatus.NOT_FOUND, "Producto no encontrado con id: " + productId);
        }
        return repository.findByProductIdAndQuantityRemainingGreaterThanOrderByReceivedAtAsc(productId, 0)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public InventoryLotResponse findById(Long id) {
        InventoryLot lot = repository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Lote no encontrado con id: " + id));
        return mapper.toResponse(lot);
    }
}
