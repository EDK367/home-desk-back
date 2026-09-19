package com.solairy.homedesk.sales.service.impl;

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
import com.solairy.homedesk.payment.dto.request.SalePaymentRequest;
import com.solairy.homedesk.payment.model.PaymentMethod;
import com.solairy.homedesk.payment.model.SalePayment;
import com.solairy.homedesk.payment.repository.PaymentMethodRepository;
import com.solairy.homedesk.payment.repository.SalePaymentRepository;
import com.solairy.homedesk.sales.dto.request.SaleItemRequest;
import com.solairy.homedesk.sales.dto.request.SaleRequest;
import com.solairy.homedesk.sales.dto.request.SaleStatusChangeRequest;
import com.solairy.homedesk.sales.dto.response.SaleResponse;
import com.solairy.homedesk.sales.mapper.SaleMapper;
import com.solairy.homedesk.sales.model.Customer;
import com.solairy.homedesk.sales.model.Sale;
import com.solairy.homedesk.sales.model.SaleItem;
import com.solairy.homedesk.sales.model.SaleStatus;
import com.solairy.homedesk.sales.repository.CustomerRepository;
import com.solairy.homedesk.sales.repository.SaleItemRepository;
import com.solairy.homedesk.sales.repository.SaleRepository;
import com.solairy.homedesk.sales.repository.SaleStatusRepository;
import com.solairy.homedesk.sales.service.SaleService;
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
public class SaleServiceImpl implements SaleService {

    private final SaleRepository repository;
    private final SaleItemRepository saleItemRepository;
    private final SaleStatusRepository saleStatusRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final InventoryRepository inventoryRepository;
    private final InventoryLotRepository inventoryLotRepository;
    private final InventoryMovementRepository inventoryMovementRepository;
    private final InventoryMovementLotRepository inventoryMovementLotRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final SalePaymentRepository salePaymentRepository;
    private final SaleMapper mapper;
    private final AuditLogService auditLogService;

    @Override
    @Transactional(readOnly = true)
    public PageResponse<SaleResponse> findAll(Pageable pageable) {
        Page<Sale> page = repository.findAll(pageable);
        return toPageResponse(page);
    }

    @Override
    @Transactional(readOnly = true)
    public SaleResponse findById(Long id) {
        Sale sale = repository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Venta no encontrada con id: " + id));
        return toResponse(sale);
    }

    @Override
    @Transactional(readOnly = true)
    public SaleResponse findByInvoiceNumber(String invoiceNumber) {
        Sale sale = repository.findByInvoiceNumber(invoiceNumber)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Venta no encontrada con número de factura: " + invoiceNumber));
        return toResponse(sale);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<SaleResponse> findByCustomer(Long customerId, Pageable pageable) {
        if (!customerRepository.existsById(customerId)) {
            throw new GlobalException(HttpStatus.NOT_FOUND, "Cliente no encontrado con id: " + customerId);
        }
        Page<Sale> page = repository.findByCustomerId(customerId, pageable);
        return toPageResponse(page);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<SaleResponse> findByStatus(Long statusId, Pageable pageable) {
        if (!saleStatusRepository.existsById(statusId)) {
            throw new GlobalException(HttpStatus.NOT_FOUND, "Estado de venta no encontrado con id: " + statusId);
        }
        Page<Sale> page = repository.findByStatusId(statusId, pageable);
        return toPageResponse(page);
    }

    @Override
    @Transactional
    public SaleResponse create(SaleRequest request) {
        User currentUser = getCurrentUser();

        Customer customer = customerRepository.findById(request.customerId())
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Cliente no encontrado con id: " + request.customerId()));

        if (Boolean.FALSE.equals(customer.getIsActive())) {
            throw new GlobalException(HttpStatus.CONFLICT, "No se puede registrar una venta a un cliente inactivo");
        }

        if (request.items() == null || request.items().isEmpty()) {
            throw new GlobalException(HttpStatus.BAD_REQUEST, "La venta debe tener al menos un producto");
        }

        long distinctProducts = request.items().stream().map(SaleItemRequest::productId).distinct().count();
        if (distinctProducts != request.items().size()) {
            throw new GlobalException(HttpStatus.BAD_REQUEST, "La venta contiene productos duplicados");
        }

        BigDecimal subtotal = BigDecimal.ZERO;
        List<Product> products = new ArrayList<>();
        List<BigDecimal> unitPrices = new ArrayList<>();

        for (SaleItemRequest itemReq : request.items()) {
            Product product = productRepository.findById(itemReq.productId())
                    .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Producto no encontrado con id: " + itemReq.productId()));

            if (Boolean.FALSE.equals(product.getIsActive())) {
                throw new GlobalException(HttpStatus.CONFLICT, "El producto '" + product.getName() + "' se encuentra inactivo");
            }

            if (itemReq.quantity() <= 0) {
                throw new GlobalException(HttpStatus.BAD_REQUEST, "La cantidad debe ser mayor a 0 para el producto: " + product.getName());
            }

            BigDecimal unitPrice = itemReq.unitPrice() != null ? itemReq.unitPrice() : product.getSalePrice();
            if (unitPrice == null || unitPrice.compareTo(BigDecimal.ZERO) <= 0) {
                throw new GlobalException(HttpStatus.BAD_REQUEST, "El precio unitario debe ser mayor a 0 para el producto: " + product.getName());
            }

            Inventory inventory = inventoryRepository.findByProductId(product.getId()).orElse(null);
            int availableStock = inventory != null ? inventory.getQuantityOnHand() : 0;
            if (availableStock < itemReq.quantity()) {
                throw new GlobalException(HttpStatus.CONFLICT, "Stock insuficiente para el producto: " + product.getName()
                        + ". Stock disponible: " + availableStock + ", solicitado: " + itemReq.quantity());
            }

            BigDecimal itemSubtotal = unitPrice.multiply(BigDecimal.valueOf(itemReq.quantity())).setScale(2, RoundingMode.HALF_UP);
            subtotal = subtotal.add(itemSubtotal);
            products.add(product);
            unitPrices.add(unitPrice);
        }

        BigDecimal taxAmount = subtotal.multiply(new BigDecimal("0.12")).setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalAmount = subtotal.add(taxAmount);

        String invoiceNumber;
        if (request.invoiceNumber() != null && !request.invoiceNumber().isBlank()) {
            invoiceNumber = request.invoiceNumber().trim();
            if (repository.existsByInvoiceNumber(invoiceNumber)) {
                throw new GlobalException(HttpStatus.CONFLICT, "Ya existe una venta con el número de factura: " + invoiceNumber);
            }
        } else {
            invoiceNumber = generateInvoiceNumber();
        }

        SaleStatus status;
        if (request.statusId() != null) {
            status = saleStatusRepository.findById(request.statusId())
                    .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Estado de venta no encontrado con id: " + request.statusId()));
        } else {
            status = saleStatusRepository.findByName("COMPLETADA")
                    .orElseGet(() -> saleStatusRepository.findByName("PAGADA")
                            .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Estado de venta no encontrado")));
        }

        String pdfPath = request.invoicePdfPath() != null && !request.invoicePdfPath().isBlank()
                ? request.invoicePdfPath().trim()
                : "/invoices/" + OffsetDateTime.now().getYear() + "/" + invoiceNumber + ".pdf";

        Sale sale = new Sale();
        sale.setCustomer(customer);
        sale.setUser(currentUser);
        sale.setStatus(status);
        sale.setInvoiceNumber(invoiceNumber);
        sale.setSaleDate(request.saleDate() != null ? request.saleDate() : OffsetDateTime.now());
        sale.setSubtotal(subtotal);
        sale.setTaxAmount(taxAmount);
        sale.setTotalAmount(totalAmount);
        sale.setInvoicePdfPath(pdfPath);
        sale.setCreatedAt(OffsetDateTime.now());
        sale.setUpdatedAt(OffsetDateTime.now());

        Sale savedSale = repository.save(sale);

        List<SaleItem> savedItems = new ArrayList<>();
        for (int i = 0; i < request.items().size(); i++) {
            SaleItemRequest itemReq = request.items().get(i);
            Product product = products.get(i);
            BigDecimal unitPrice = unitPrices.get(i);
            BigDecimal itemSubtotal = unitPrice.multiply(BigDecimal.valueOf(itemReq.quantity())).setScale(2, RoundingMode.HALF_UP);

            SaleItem item = new SaleItem();
            item.setSale(savedSale);
            item.setProduct(product);
            item.setQuantity(itemReq.quantity());
            item.setUnitPrice(unitPrice);
            item.setSubtotal(itemSubtotal);
            item.setCreatedAt(OffsetDateTime.now());
            savedItems.add(saleItemRepository.save(item));
        }

        List<SalePayment> savedPayments = new ArrayList<>();
        if (request.payments() != null && !request.payments().isEmpty()) {
            for (SalePaymentRequest pReq : request.payments()) {
                PaymentMethod pm = paymentMethodRepository.findById(pReq.paymentMethodId())
                        .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Método de pago no encontrado con id: " + pReq.paymentMethodId()));

                if (Boolean.FALSE.equals(pm.getIsActive())) {
                    throw new GlobalException(HttpStatus.CONFLICT, "El método de pago '" + pm.getName() + "' se encuentra inactivo");
                }

                if (pReq.amount() == null || pReq.amount().compareTo(BigDecimal.ZERO) <= 0) {
                    throw new GlobalException(HttpStatus.BAD_REQUEST, "El monto del pago debe ser mayor a 0");
                }

                SalePayment payment = new SalePayment();
                payment.setSale(savedSale);
                payment.setPaymentMethod(pm);
                payment.setAmount(pReq.amount());
                payment.setPaymentDate(pReq.paymentDate() != null ? pReq.paymentDate() : OffsetDateTime.now());
                payment.setReferenceNumber(pReq.referenceNumber() != null ? pReq.referenceNumber().trim() : null);
                payment.setNotes(pReq.notes() != null ? pReq.notes().trim() : null);
                payment.setCreatedAt(OffsetDateTime.now());
                savedPayments.add(salePaymentRepository.save(payment));
            }
        }

        if (isDispatchedStatus(status.getName())) {
            processInventoryDispatch(savedSale, savedItems, currentUser);
        }

        auditLogService.log(currentUser, "CREATE", "VENTAS", "Sale", savedSale.getId().toString(), "Venta registrada con factura " + savedSale.getInvoiceNumber() + " por monto total Q" + savedSale.getTotalAmount());

        return mapper.toResponse(savedSale, savedItems, savedPayments);
    }

    @Override
    @Transactional
    public SaleResponse updateStatus(Long id, SaleStatusChangeRequest request) {
        Sale sale = repository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Venta no encontrada con id: " + id));

        SaleStatus newStatus = saleStatusRepository.findById(request.statusId())
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Estado de venta no encontrado con id: " + request.statusId()));

        if (sale.getStatus().getId().equals(newStatus.getId())) {
            throw new GlobalException(HttpStatus.CONFLICT, "La venta ya se encuentra en el estado: " + newStatus.getName());
        }

        String currentStatusName = sale.getStatus().getName();
        String newStatusName = newStatus.getName();

        if ("CANCELADA".equalsIgnoreCase(currentStatusName)) {
            throw new GlobalException(HttpStatus.CONFLICT, "No se puede cambiar el estado de una venta cancelada");
        }

        boolean wasDispatched = isDispatchedStatus(currentStatusName);
        boolean willBeDispatched = isDispatchedStatus(newStatusName);

        User currentUser = getCurrentUser();
        List<SaleItem> items = saleItemRepository.findBySaleId(id);

        if (!wasDispatched && willBeDispatched) {
            processInventoryDispatch(sale, items, currentUser);
        } else if ("CANCELADA".equalsIgnoreCase(newStatusName) && wasDispatched) {
            rollbackInventoryDispatch(sale, items, currentUser);
        }

        sale.setStatus(newStatus);
        sale.setUpdatedAt(OffsetDateTime.now());
        Sale saved = repository.save(sale);

        auditLogService.log(currentUser, "UPDATE", "VENTAS", "Sale", saved.getId().toString(), "Estado de venta #" + saved.getId() + " actualizado a " + newStatus.getName());

        return toResponse(saved);
    }

    @Override
    @Transactional
    public void cancel(Long id) {
        SaleStatus cancelStatus = saleStatusRepository.findByName("CANCELADA")
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Estado de venta CANCELADA no encontrado"));
        updateStatus(id, new SaleStatusChangeRequest(cancelStatus.getId()));
    }

    private void processInventoryDispatch(Sale sale, List<SaleItem> items, User currentUser) {
        for (SaleItem item : items) {
            Product product = item.getProduct();

            Inventory inventory = inventoryRepository.findByProductId(product.getId())
                    .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Inventario no encontrado para el producto: " + product.getName()));

            if (inventory.getQuantityOnHand() < item.getQuantity()) {
                throw new GlobalException(HttpStatus.CONFLICT, "Stock insuficiente para el producto: " + product.getName()
                        + ". Stock actual: " + inventory.getQuantityOnHand() + ", solicitado: " + item.getQuantity());
            }

            int newBalance = inventory.getQuantityOnHand() - item.getQuantity();
            inventory.setQuantityOnHand(newBalance);
            inventory.setUpdatedAt(OffsetDateTime.now());
            inventoryRepository.save(inventory);

            InventoryMovement movement = new InventoryMovement();
            movement.setProduct(product);
            movement.setUser(currentUser);
            movement.setMovementType("SALIDA_VENTA");
            movement.setQuantity(-item.getQuantity());
            movement.setBalanceAfter(newBalance);
            movement.setReferenceType("SALE");
            movement.setReferenceId(sale.getId());
            movement.setCostingMethod("PEPS");
            movement.setNotes("Salida por venta " + sale.getInvoiceNumber());
            movement.setCreatedAt(OffsetDateTime.now());
            InventoryMovement savedMovement = inventoryMovementRepository.save(movement);

            List<InventoryLot> availableLots = inventoryLotRepository
                    .findByProductIdAndQuantityRemainingGreaterThanOrderByReceivedAtAsc(product.getId(), 0);

            int needed = item.getQuantity();
            for (InventoryLot lot : availableLots) {
                if (needed <= 0) {
                    break;
                }
                int take = Math.min(lot.getQuantityRemaining(), needed);
                lot.setQuantityRemaining(lot.getQuantityRemaining() - take);
                inventoryLotRepository.save(lot);

                InventoryMovementLot movementLot = new InventoryMovementLot();
                movementLot.setMovement(savedMovement);
                movementLot.setLot(lot);
                movementLot.setQuantity(take);
                movementLot.setUnitCost(lot.getUnitCost());
                inventoryMovementLotRepository.save(movementLot);

                needed -= take;
            }

            if (needed > 0) {
                throw new GlobalException(HttpStatus.CONFLICT, "No hay suficientes lotes disponibles en inventario para despachar el producto: " + product.getName());
            }
        }
    }

    private void rollbackInventoryDispatch(Sale sale, List<SaleItem> items, User currentUser) {
        List<InventoryMovement> saleMovements = inventoryMovementRepository
                .findByReferenceTypeAndReferenceId("SALE", sale.getId());

        List<InventoryMovementLot> movementLots = inventoryMovementLotRepository
                .findByMovementIn(saleMovements);

        for (InventoryMovementLot mLot : movementLots) {
            InventoryLot lot = mLot.getLot();
            lot.setQuantityRemaining(lot.getQuantityRemaining() + mLot.getQuantity());
            inventoryLotRepository.save(lot);
        }

        for (SaleItem item : items) {
            Product product = item.getProduct();
            Inventory inventory = inventoryRepository.findByProductId(product.getId())
                    .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Inventario no encontrado para el producto: " + product.getName()));

            int newBalance = inventory.getQuantityOnHand() + item.getQuantity();
            inventory.setQuantityOnHand(newBalance);
            inventory.setUpdatedAt(OffsetDateTime.now());
            inventoryRepository.save(inventory);

            InventoryMovement movement = new InventoryMovement();
            movement.setProduct(product);
            movement.setUser(currentUser);
            movement.setMovementType("AJUSTE_MANUAL");
            movement.setQuantity(item.getQuantity());
            movement.setBalanceAfter(newBalance);
            movement.setReferenceType("SALE");
            movement.setReferenceId(sale.getId());
            movement.setCostingMethod("PEPS");
            movement.setNotes("Reversión por cancelación de venta " + sale.getInvoiceNumber());
            movement.setCreatedAt(OffsetDateTime.now());
            inventoryMovementRepository.save(movement);
        }
    }

    private boolean isDispatchedStatus(String statusName) {
        return "COMPLETADA".equalsIgnoreCase(statusName)
                || "PAGADA".equalsIgnoreCase(statusName)
                || "ENTREGADA".equalsIgnoreCase(statusName);
    }

    private synchronized String generateInvoiceNumber() {
        long count = repository.count() + 1;
        String candidate = String.format("FACT-001-%05d", count);
        while (repository.existsByInvoiceNumber(candidate)) {
            count++;
            candidate = String.format("FACT-001-%05d", count);
        }
        return candidate;
    }

    private SaleResponse toResponse(Sale sale) {
        List<SaleItem> items = saleItemRepository.findBySaleId(sale.getId());
        List<SalePayment> payments = salePaymentRepository.findBySaleId(sale.getId());
        return mapper.toResponse(sale, items, payments);
    }

    private PageResponse<SaleResponse> toPageResponse(Page<Sale> page) {
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
