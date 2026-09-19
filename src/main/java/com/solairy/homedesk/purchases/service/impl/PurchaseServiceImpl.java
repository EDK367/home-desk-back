package com.solairy.homedesk.purchases.service.impl;

import com.solairy.homedesk.auth.model.User;
import com.solairy.homedesk.auth.repository.UserRepository;
import com.solairy.homedesk.catalog.model.Product;
import com.solairy.homedesk.catalog.repository.ProductRepository;
import com.solairy.homedesk.common.exception.GlobalException;
import com.solairy.homedesk.common.response.PageResponse;
import com.solairy.homedesk.inventory.model.Inventory;
import com.solairy.homedesk.inventory.model.InventoryLot;
import com.solairy.homedesk.inventory.model.InventoryMovement;
import com.solairy.homedesk.inventory.model.InventoryMovementLot;
import com.solairy.homedesk.inventory.repository.InventoryLotRepository;
import com.solairy.homedesk.inventory.repository.InventoryMovementLotRepository;
import com.solairy.homedesk.inventory.repository.InventoryMovementRepository;
import com.solairy.homedesk.inventory.repository.InventoryRepository;
import com.solairy.homedesk.purchases.dto.request.PurchaseItemRequest;
import com.solairy.homedesk.purchases.dto.request.PurchaseRequest;
import com.solairy.homedesk.purchases.dto.request.PurchaseStatusChangeRequest;
import com.solairy.homedesk.purchases.dto.response.PurchaseResponse;
import com.solairy.homedesk.purchases.mapper.PurchaseMapper;
import com.solairy.homedesk.purchases.model.Purchase;
import com.solairy.homedesk.purchases.model.PurchaseItem;
import com.solairy.homedesk.purchases.model.PurchaseStatus;
import com.solairy.homedesk.purchases.repository.PurchaseItemRepository;
import com.solairy.homedesk.purchases.repository.PurchaseRepository;
import com.solairy.homedesk.purchases.repository.PurchaseStatusRepository;
import com.solairy.homedesk.purchases.service.PurchaseService;
import com.solairy.homedesk.suppliers.model.Supplier;
import com.solairy.homedesk.suppliers.model.SupplierProduct;
import com.solairy.homedesk.suppliers.repository.SupplierProductRepository;
import com.solairy.homedesk.suppliers.repository.SupplierRepository;
import com.solairy.homedesk.audit.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository repository;
    private final PurchaseItemRepository purchaseItemRepository;
    private final PurchaseStatusRepository purchaseStatusRepository;
    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final InventoryRepository inventoryRepository;
    private final InventoryLotRepository inventoryLotRepository;
    private final InventoryMovementRepository inventoryMovementRepository;
    private final InventoryMovementLotRepository inventoryMovementLotRepository;
    private final SupplierProductRepository supplierProductRepository;
    private final PurchaseMapper mapper;
    private final AuditLogService auditLogService;

    @Override
    @Transactional(readOnly = true)
    public PageResponse<PurchaseResponse> findAll(Pageable pageable) {
        Page<Purchase> page = repository.findAll(pageable);
        return toPageResponse(page);
    }

    @Override
    @Transactional(readOnly = true)
    public PurchaseResponse findById(Long id) {
        Purchase purchase = repository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Compra no encontrada con id: " + id));
        return toResponse(purchase);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<PurchaseResponse> findBySupplier(Long supplierId, Pageable pageable) {
        if (!supplierRepository.existsById(supplierId)) {
            throw new GlobalException(HttpStatus.NOT_FOUND, "Proveedor no encontrado con id: " + supplierId);
        }
        Page<Purchase> page = repository.findBySupplierId(supplierId, pageable);
        return toPageResponse(page);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<PurchaseResponse> findByStatus(Long statusId, Pageable pageable) {
        if (!purchaseStatusRepository.existsById(statusId)) {
            throw new GlobalException(HttpStatus.NOT_FOUND, "Estado de compra no encontrado con id: " + statusId);
        }
        Page<Purchase> page = repository.findByStatusId(statusId, pageable);
        return toPageResponse(page);
    }

    @Override
    @Transactional
    public PurchaseResponse create(PurchaseRequest request) {
        User currentUser = getCurrentUser();

        Supplier supplier = supplierRepository.findById(request.supplierId())
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Proveedor no encontrado con id: " + request.supplierId()));

        if (Boolean.FALSE.equals(supplier.getIsActive())) {
            throw new GlobalException(HttpStatus.CONFLICT, "No se puede registrar una compra a un proveedor inactivo");
        }

        if (request.items() == null || request.items().isEmpty()) {
            throw new GlobalException(HttpStatus.BAD_REQUEST, "La compra debe tener al menos un producto");
        }

        long distinctProducts = request.items().stream().map(PurchaseItemRequest::productId).distinct().count();
        if (distinctProducts != request.items().size()) {
            throw new GlobalException(HttpStatus.BAD_REQUEST, "La orden de compra contiene productos duplicados");
        }

        BigDecimal subtotal = BigDecimal.ZERO;
        List<Product> products = new ArrayList<>();
        for (PurchaseItemRequest itemReq : request.items()) {
            Product product = productRepository.findById(itemReq.productId())
                    .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Producto no encontrado con id: " + itemReq.productId()));

            if (Boolean.FALSE.equals(product.getIsActive())) {
                throw new GlobalException(HttpStatus.CONFLICT, "El producto '" + product.getName() + "' se encuentra inactivo");
            }

            if (itemReq.quantity() <= 0) {
                throw new GlobalException(HttpStatus.BAD_REQUEST, "La cantidad debe ser mayor a 0 para el producto: " + product.getName());
            }

            if (itemReq.unitCost() == null || itemReq.unitCost().compareTo(BigDecimal.ZERO) <= 0) {
                throw new GlobalException(HttpStatus.BAD_REQUEST, "El costo unitario debe ser mayor a 0 para el producto: " + product.getName());
            }

            BigDecimal itemSubtotal = itemReq.unitCost().multiply(BigDecimal.valueOf(itemReq.quantity())).setScale(2, RoundingMode.HALF_UP);
            subtotal = subtotal.add(itemSubtotal);
            products.add(product);
        }

        BigDecimal taxAmount = subtotal.multiply(new BigDecimal("0.12")).setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalAmount = subtotal.add(taxAmount);

        PurchaseStatus status;
        if (request.statusId() != null) {
            status = purchaseStatusRepository.findById(request.statusId())
                    .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Estado de compra no encontrado con id: " + request.statusId()));
        } else {
            status = purchaseStatusRepository.findByName("RECIBIDA")
                    .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Estado de compra RECIBIDA no encontrado"));
        }

        Purchase purchase = new Purchase();
        purchase.setSupplier(supplier);
        purchase.setUser(currentUser);
        purchase.setStatus(status);
        purchase.setPurchaseDate(request.purchaseDate() != null ? request.purchaseDate() : OffsetDateTime.now());
        purchase.setSubtotal(subtotal);
        purchase.setTaxAmount(taxAmount);
        purchase.setTotalAmount(totalAmount);
        purchase.setNotes(request.notes() != null ? request.notes().trim() : null);
        purchase.setCreatedAt(OffsetDateTime.now());
        purchase.setUpdatedAt(OffsetDateTime.now());

        Purchase savedPurchase = repository.save(purchase);

        List<PurchaseItem> savedItems = new ArrayList<>();
        for (int i = 0; i < request.items().size(); i++) {
            PurchaseItemRequest itemReq = request.items().get(i);
            Product product = products.get(i);
            BigDecimal itemSubtotal = itemReq.unitCost().multiply(BigDecimal.valueOf(itemReq.quantity())).setScale(2, RoundingMode.HALF_UP);

            PurchaseItem item = new PurchaseItem();
            item.setPurchase(savedPurchase);
            item.setProduct(product);
            item.setQuantity(itemReq.quantity());
            item.setUnitCost(itemReq.unitCost());
            item.setSubtotal(itemSubtotal);
            item.setCreatedAt(OffsetDateTime.now());
            savedItems.add(purchaseItemRepository.save(item));
        }

        if ("RECIBIDA".equalsIgnoreCase(status.getName())) {
            processInventoryReception(savedPurchase, savedItems, currentUser);
        }

        auditLogService.log(currentUser, "CREATE", "COMPRAS", "Purchase", savedPurchase.getId().toString(), "Compra registrada #" + savedPurchase.getId() + " con proveedor " + supplier.getName() + " por monto total Q" + savedPurchase.getTotalAmount());

        return mapper.toResponse(savedPurchase, savedItems);
    }

    @Override
    @Transactional
    public PurchaseResponse updateStatus(Long id, PurchaseStatusChangeRequest request) {
        Purchase purchase = repository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Compra no encontrada con id: " + id));

        PurchaseStatus newStatus = purchaseStatusRepository.findById(request.statusId())
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Estado de compra no encontrado con id: " + request.statusId()));

        if (purchase.getStatus().getId().equals(newStatus.getId())) {
            throw new GlobalException(HttpStatus.CONFLICT, "La compra ya se encuentra en el estado: " + newStatus.getName());
        }

        String currentStatusName = purchase.getStatus().getName();
        String newStatusName = newStatus.getName();

        if ("CANCELADA".equalsIgnoreCase(currentStatusName)) {
            throw new GlobalException(HttpStatus.CONFLICT, "No se puede cambiar el estado de una compra cancelada");
        }

        List<PurchaseItem> items = purchaseItemRepository.findByPurchaseId(purchase.getId());
        User currentUser = getCurrentUser();

        if ("RECIBIDA".equalsIgnoreCase(newStatusName)) {
            if ("RECIBIDA".equalsIgnoreCase(currentStatusName)) {
                throw new GlobalException(HttpStatus.CONFLICT, "La compra ya fue recibida");
            }
            processInventoryReception(purchase, items, currentUser);
        } else if ("CANCELADA".equalsIgnoreCase(newStatusName)) {
            if ("RECIBIDA".equalsIgnoreCase(currentStatusName)) {
                List<InventoryLot> lots = inventoryLotRepository.findByPurchaseItemIn(items);
                for (InventoryLot lot : lots) {
                    if (lot.getQuantityRemaining() < lot.getQuantityReceived()) {
                        throw new GlobalException(HttpStatus.CONFLICT,
                                "No se puede cancelar la compra porque los productos ya han sido utilizados o despachados del inventario");
                    }
                }

                for (PurchaseItem item : items) {
                    Product product = item.getProduct();
                    Inventory inventory = inventoryRepository.findByProductId(product.getId())
                            .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Inventario no encontrado para el producto: " + product.getName()));

                    int newBalance = inventory.getQuantityOnHand() - item.getQuantity();
                    if (newBalance < 0) {
                        throw new GlobalException(HttpStatus.CONFLICT, "No se puede cancelar la compra porque resultaría en stock negativo");
                    }
                    inventory.setQuantityOnHand(newBalance);
                    inventory.setUpdatedAt(OffsetDateTime.now());
                    inventoryRepository.save(inventory);

                    InventoryMovement movement = new InventoryMovement();
                    movement.setProduct(product);
                    movement.setUser(currentUser);
                    movement.setMovementType("AJUSTE_MANUAL");
                    movement.setQuantity(-item.getQuantity());
                    movement.setBalanceAfter(newBalance);
                    movement.setReferenceType("PURCHASE");
                    movement.setReferenceId(purchase.getId());
                    movement.setCostingMethod("PEPS");
                    movement.setNotes("Reversión por cancelación de compra #" + purchase.getId());
                    movement.setCreatedAt(OffsetDateTime.now());
                    inventoryMovementRepository.save(movement);
                }

                inventoryLotRepository.deleteAll(lots);
            }
        }

        purchase.setStatus(newStatus);
        purchase.setUpdatedAt(OffsetDateTime.now());
        Purchase saved = repository.save(purchase);

        auditLogService.log(currentUser, "UPDATE", "COMPRAS", "Purchase", saved.getId().toString(), "Estado de compra #" + saved.getId() + " actualizado a " + newStatus.getName());

        return mapper.toResponse(saved, items);
    }

    @Override
    @Transactional
    public void cancel(Long id) {
        PurchaseStatus cancelStatus = purchaseStatusRepository.findByName("CANCELADA")
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Estado de compra CANCELADA no encontrado"));
        updateStatus(id, new PurchaseStatusChangeRequest(cancelStatus.getId()));
    }

    private void processInventoryReception(Purchase purchase, List<PurchaseItem> items, User currentUser) {
        for (PurchaseItem item : items) {
            Product product = item.getProduct();

            InventoryLot lot = new InventoryLot();
            lot.setProduct(product);
            lot.setPurchaseItem(item);
            lot.setQuantityReceived(item.getQuantity());
            lot.setQuantityRemaining(item.getQuantity());
            lot.setUnitCost(item.getUnitCost());
            lot.setReceivedAt(purchase.getPurchaseDate());
            lot.setCreatedAt(OffsetDateTime.now());
            InventoryLot savedLot = inventoryLotRepository.save(lot);

            Inventory inventory = inventoryRepository.findByProductId(product.getId())
                    .orElseGet(() -> {
                        Inventory newInv = new Inventory();
                        newInv.setProduct(product);
                        newInv.setQuantityOnHand(0);
                        newInv.setUpdatedAt(OffsetDateTime.now());
                        return inventoryRepository.save(newInv);
                    });

            int newBalance = inventory.getQuantityOnHand() + item.getQuantity();
            inventory.setQuantityOnHand(newBalance);
            inventory.setUpdatedAt(OffsetDateTime.now());
            inventoryRepository.save(inventory);

            InventoryMovement movement = new InventoryMovement();
            movement.setProduct(product);
            movement.setUser(currentUser);
            movement.setMovementType("ENTRADA_COMPRA");
            movement.setQuantity(item.getQuantity());
            movement.setBalanceAfter(newBalance);
            movement.setReferenceType("PURCHASE");
            movement.setReferenceId(purchase.getId());
            movement.setCostingMethod("PEPS");
            movement.setNotes("Ingreso por compra #" + purchase.getId());
            movement.setCreatedAt(OffsetDateTime.now());
            InventoryMovement savedMovement = inventoryMovementRepository.save(movement);

            InventoryMovementLot movementLot = new InventoryMovementLot();
            movementLot.setMovement(savedMovement);
            movementLot.setLot(savedLot);
            movementLot.setQuantity(item.getQuantity());
            movementLot.setUnitCost(item.getUnitCost());
            inventoryMovementLotRepository.save(movementLot);

            SupplierProduct supplierProduct = supplierProductRepository
                    .findBySupplierIdAndProductId(purchase.getSupplier().getId(), product.getId())
                    .orElseGet(() -> {
                        SupplierProduct newSp = new SupplierProduct();
                        newSp.setSupplier(purchase.getSupplier());
                        newSp.setProduct(product);
                        newSp.setSupplierSku(product.getSku());
                        newSp.setCost(item.getUnitCost());
                        newSp.setCreatedAt(OffsetDateTime.now());
                        return newSp;
                    });
            supplierProduct.setLastPurchaseCost(item.getUnitCost());
            supplierProduct.setUpdatedAt(OffsetDateTime.now());
            supplierProductRepository.save(supplierProduct);
        }
    }

    private PurchaseResponse toResponse(Purchase purchase) {
        List<PurchaseItem> items = purchaseItemRepository.findByPurchaseId(purchase.getId());
        return mapper.toResponse(purchase, items);
    }

    private PageResponse<PurchaseResponse> toPageResponse(Page<Purchase> page) {
        return PageResponse.from(page, this::toResponse);
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
