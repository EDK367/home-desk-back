package com.solairy.homedesk.inventory.service.impl;

import com.solairy.homedesk.common.exception.GlobalException;
import com.solairy.homedesk.inventory.dto.response.InventoryMovementLotResponse;
import com.solairy.homedesk.inventory.mapper.InventoryMovementLotMapper;
import com.solairy.homedesk.inventory.model.InventoryMovementLot;
import com.solairy.homedesk.inventory.repository.InventoryMovementLotRepository;
import com.solairy.homedesk.inventory.repository.InventoryMovementRepository;
import com.solairy.homedesk.inventory.service.InventoryMovementLotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryMovementLotServiceImpl implements InventoryMovementLotService {

    private final InventoryMovementLotRepository repository;
    private final InventoryMovementRepository movementRepository;
    private final InventoryMovementLotMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<InventoryMovementLotResponse> findByMovementId(Long movementId) {
        if (!movementRepository.existsById(movementId)) {
            throw new GlobalException(HttpStatus.NOT_FOUND, "Movimiento de inventario no encontrado con id: " + movementId);
        }
        return repository.findByMovementId(movementId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<InventoryMovementLotResponse> findByLotId(Long lotId) {
        return repository.findByLotId(lotId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public InventoryMovementLotResponse findById(Long id) {
        InventoryMovementLot movementLot = repository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Detalle de lote de movimiento no encontrado con id: " + id));
        return mapper.toResponse(movementLot);
    }
}
