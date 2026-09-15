package com.solairy.homedesk.inventory.service.impl;

import com.solairy.homedesk.auth.model.User;
import com.solairy.homedesk.auth.repository.UserRepository;
import com.solairy.homedesk.catalog.model.Product;
import com.solairy.homedesk.catalog.repository.ProductRepository;
import com.solairy.homedesk.common.exception.GlobalException;
import com.solairy.homedesk.common.response.PageResponse;
import com.solairy.homedesk.inventory.dto.request.ManualStockAdjustmentRequest;
import com.solairy.homedesk.inventory.dto.response.InventoryResponse;
import com.solairy.homedesk.inventory.mapper.InventoryMapper;
import com.solairy.homedesk.inventory.model.Inventory;
import com.solairy.homedesk.inventory.model.InventoryMovement;
import com.solairy.homedesk.inventory.repository.InventoryMovementRepository;
import com.solairy.homedesk.inventory.repository.InventoryRepository;
import com.solairy.homedesk.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository;
    private final ProductRepository productRepository;
    private final InventoryMovementRepository inventoryMovementRepository;
    private final UserRepository userRepository;
    private final InventoryMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public PageResponse<InventoryResponse> findAll(Pageable pageable) {
        Page<Inventory> page = repository.findAll(pageable);
        return PageResponse.from(page, mapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<InventoryResponse> findLowStock(Pageable pageable) {
        Page<Inventory> page = repository.findLowStock(pageable);
        return PageResponse.from(page, mapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<InventoryResponse> findByCategory(Long categoryId, Pageable pageable) {
        Page<Inventory> page = repository.findByProductCategoryId(categoryId, pageable);
        return PageResponse.from(page, mapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public InventoryResponse findById(Long id) {
        Inventory inventory = repository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Inventario no encontrado con id: " + id));
        return mapper.toResponse(inventory);
    }

    @Override
    @Transactional(readOnly = true)
    public InventoryResponse findByProductId(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new GlobalException(HttpStatus.NOT_FOUND, "Producto no encontrado con id: " + productId);
        }
        Inventory inventory = repository.findByProductId(productId)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Inventario no encontrado para el producto con id: " + productId));
        return mapper.toResponse(inventory);
    }

    @Override
    @Transactional
    public InventoryResponse adjustStock(Long productId, ManualStockAdjustmentRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Producto no encontrado con id: " + productId));

        if (Boolean.FALSE.equals(product.getIsActive())) {
            throw new GlobalException(HttpStatus.CONFLICT, "No se puede modificar el stock de un producto inactivo");
        }

        if (request.quantity() == 0) {
            throw new GlobalException(HttpStatus.BAD_REQUEST, "La cantidad del ajuste no puede ser cero");
        }

        Inventory inventory = repository.findByProductId(productId)
                .orElseGet(() -> {
                    Inventory newInv = new Inventory();
                    newInv.setProduct(product);
                    newInv.setQuantityOnHand(0);
                    newInv.setUpdatedAt(OffsetDateTime.now());
                    return repository.save(newInv);
                });

        int newBalance = inventory.getQuantityOnHand() + request.quantity();
        if (newBalance < 0) {
            throw new GlobalException(HttpStatus.BAD_REQUEST,
                    "El ajuste dejaría el stock en negativo. Stock actual: " + inventory.getQuantityOnHand() + ", ajuste solicitado: " + request.quantity());
        }

        inventory.setQuantityOnHand(newBalance);
        inventory.setUpdatedAt(OffsetDateTime.now());
        Inventory savedInventory = repository.save(inventory);

        User currentUser = getCurrentUser();

        InventoryMovement movement = new InventoryMovement();
        movement.setProduct(product);
        movement.setUser(currentUser);
        movement.setMovementType(request.quantity() > 0 ? "AJUSTE_ENTRADA" : "AJUSTE_SALIDA");
        movement.setQuantity(request.quantity());
        movement.setBalanceAfter(newBalance);
        movement.setReferenceType("MANUAL_ADJUSTMENT");
        movement.setCostingMethod("PEPS");
        movement.setNotes(request.notes().trim());
        movement.setCreatedAt(OffsetDateTime.now());
        inventoryMovementRepository.save(movement);

        return mapper.toResponse(savedInventory);
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getName() == null) {
            throw new GlobalException(HttpStatus.UNAUTHORIZED, "Usuario no autenticado");
        }
        try {
            UUID userId = UUID.fromString(auth.getName());
            return userRepository.findById(userId)
                    .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        } catch (IllegalArgumentException e) {
            return userRepository.findByUsernameOrEmailIgnoreCase(auth.getName())
                    .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        }
    }
}
