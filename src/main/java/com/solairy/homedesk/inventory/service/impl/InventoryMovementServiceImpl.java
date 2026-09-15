package com.solairy.homedesk.inventory.service.impl;

import com.solairy.homedesk.catalog.repository.ProductRepository;
import com.solairy.homedesk.common.exception.GlobalException;
import com.solairy.homedesk.common.response.PageResponse;
import com.solairy.homedesk.inventory.dto.response.InventoryMovementResponse;
import com.solairy.homedesk.inventory.mapper.InventoryMovementMapper;
import com.solairy.homedesk.inventory.model.InventoryMovement;
import com.solairy.homedesk.inventory.repository.InventoryMovementRepository;
import com.solairy.homedesk.inventory.service.InventoryMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class InventoryMovementServiceImpl implements InventoryMovementService {

    private final InventoryMovementRepository repository;
    private final ProductRepository productRepository;
    private final InventoryMovementMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public PageResponse<InventoryMovementResponse> findAll(Pageable pageable) {
        Page<InventoryMovement> page = repository.findAll(pageable);
        return PageResponse.from(page, mapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<InventoryMovementResponse> findByProduct(Long productId, Pageable pageable) {
        if (!productRepository.existsById(productId)) {
            throw new GlobalException(HttpStatus.NOT_FOUND, "Producto no encontrado con id: " + productId);
        }
        Page<InventoryMovement> page = repository.findByProductId(productId, pageable);
        return PageResponse.from(page, mapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<InventoryMovementResponse> findByType(String movementType, Pageable pageable) {
        Page<InventoryMovement> page = repository.findByMovementType(movementType, pageable);
        return PageResponse.from(page, mapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<InventoryMovementResponse> findByDateRange(OffsetDateTime start, OffsetDateTime end, Pageable pageable) {
        Page<InventoryMovement> page = repository.findByCreatedAtBetween(start, end, pageable);
        return PageResponse.from(page, mapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<InventoryMovementResponse> findByProductAndDateRange(Long productId, OffsetDateTime start, OffsetDateTime end, Pageable pageable) {
        if (!productRepository.existsById(productId)) {
            throw new GlobalException(HttpStatus.NOT_FOUND, "Producto no encontrado con id: " + productId);
        }
        Page<InventoryMovement> page = repository.findByProductIdAndCreatedAtBetween(productId, start, end, pageable);
        return PageResponse.from(page, mapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public InventoryMovementResponse findById(Long id) {
        InventoryMovement movement = repository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Movimiento de inventario no encontrado con id: " + id));
        return mapper.toResponse(movement);
    }
}
