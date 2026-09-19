package com.solairy.homedesk.catalog.service.impl;

import com.solairy.homedesk.auth.model.User;
import com.solairy.homedesk.auth.repository.UserRepository;
import com.solairy.homedesk.catalog.dto.request.*;
import com.solairy.homedesk.catalog.dto.response.ProductResponse;
import com.solairy.homedesk.catalog.dto.response.ProductStockResponse;
import com.solairy.homedesk.catalog.mapper.ProductMapper;
import com.solairy.homedesk.catalog.model.Category;
import com.solairy.homedesk.catalog.model.Product;
import com.solairy.homedesk.catalog.repository.CategoryRepository;
import com.solairy.homedesk.catalog.repository.ProductRepository;
import com.solairy.homedesk.catalog.service.ProductService;
import com.solairy.homedesk.common.exception.GlobalException;
import com.solairy.homedesk.common.response.PageResponse;
import com.solairy.homedesk.inventory.model.Inventory;
import com.solairy.homedesk.inventory.model.InventoryMovement;
import com.solairy.homedesk.inventory.repository.InventoryMovementRepository;
import com.solairy.homedesk.inventory.repository.InventoryRepository;
import com.solairy.homedesk.audit.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;
    private final InventoryRepository inventoryRepository;
    private final InventoryMovementRepository inventoryMovementRepository;
    private final UserRepository userRepository;
    private final ProductMapper mapper;
    private final AuditLogService auditLogService;

    @Override
    @Transactional(readOnly = true)
    public PageResponse<ProductResponse> findAll(Pageable pageable) {
        Page<Product> page = repository.findAllByIsActive(true, pageable);
        return toPageResponse(page);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<ProductResponse> findAllProductAdmin(Pageable pageable) {
        Page<Product> page = repository.findAll(pageable);
        return toPageResponse(page);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse findById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Producto no encontrado con id: " + id));

        Integer stock = inventoryRepository.findByProductId(id)
                .map(Inventory::getQuantityOnHand)
                .orElse(0);

        return mapper.toProductResponse(product, stock);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductStockResponse getStock(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Producto no encontrado con id: " + id));

        Inventory inventory = inventoryRepository.findByProductId(id)
                .orElse(null);

        Integer stock = inventory != null ? inventory.getQuantityOnHand() : 0;
        OffsetDateTime updatedAt = inventory != null ? inventory.getUpdatedAt() : product.getUpdatedAt();

        return new ProductStockResponse(
                product.getId(),
                product.getSku(),
                product.getName(),
                stock,
                product.getMinStockLevel(),
                updatedAt
        );
    }

    @Override
    @Transactional
    public ProductResponse create(ProductRequest request) {
        String sku = request.sku().trim();
        if (repository.existsBySku(sku)) {
            throw new GlobalException(HttpStatus.CONFLICT, "Ya existe un producto con el SKU: " + sku);
        }

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Categoría no encontrada con id: " + request.categoryId()));

        if (Boolean.FALSE.equals(category.getIsActive())) {
            throw new GlobalException(HttpStatus.CONFLICT, "No se puede asociar una categoría inactiva");
        }

        Product product = new Product();
        product.setSku(sku);
        product.setName(request.name().trim());
        product.setDescription(request.description() != null ? request.description().trim() : null);
        product.setCategory(category);
        product.setUnitOfMeasure(request.unitOfMeasure());
        product.setSalePrice(request.salePrice());
        product.setMinStockLevel(request.minStockLevel());
        product.setIsActive(true);
        product.setCreatedAt(OffsetDateTime.now());
        product.setUpdatedAt(OffsetDateTime.now());

        Product savedProduct = repository.save(product);

        Inventory inventory = new Inventory();
        inventory.setProduct(savedProduct);
        inventory.setQuantityOnHand(0);
        inventory.setUpdatedAt(OffsetDateTime.now());
        inventoryRepository.save(inventory);

        auditLogService.log("CREATE", "PRODUCTOS", "Product", savedProduct.getId().toString(), "Producto creado: " + savedProduct.getName() + " (SKU: " + savedProduct.getSku() + ")");

        return mapper.toProductResponse(savedProduct, 0);
    }

    @Override
    @Transactional
    public ProductResponse update(Long id, ProductRequest request) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Producto no encontrado con id: " + id));

        if (Boolean.FALSE.equals(product.getIsActive())) {
            throw new GlobalException(HttpStatus.CONFLICT, "No se puede actualizar un producto inactivo");
        }

        String sku = request.sku().trim();
        if (repository.existsBySkuAndIdNot(sku, id)) {
            throw new GlobalException(HttpStatus.CONFLICT, "Ya existe un producto con el SKU: " + sku);
        }

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Categoría no encontrada con id: " + request.categoryId()));

        if (Boolean.FALSE.equals(category.getIsActive())) {
            throw new GlobalException(HttpStatus.CONFLICT, "No se puede asociar una categoría inactiva");
        }

        if (!callerIsAdmin()) {
            if (request.salePrice() != null && request.salePrice().compareTo(product.getSalePrice()) != 0) {
                throw new GlobalException(HttpStatus.FORBIDDEN, "El rol PURCHASING no tiene permisos para modificar el precio de venta");
            }
            if (request.minStockLevel() != null && !request.minStockLevel().equals(product.getMinStockLevel())) {
                throw new GlobalException(HttpStatus.FORBIDDEN, "El rol PURCHASING no tiene permisos para modificar el stock mínimo");
            }
        }

        product.setSku(sku);
        product.setName(request.name().trim());
        product.setDescription(request.description() != null ? request.description().trim() : null);
        product.setCategory(category);
        product.setUnitOfMeasure(request.unitOfMeasure());

        if (callerIsAdmin()) {
            product.setSalePrice(request.salePrice());
            product.setMinStockLevel(request.minStockLevel());
        }

        product.setUpdatedAt(OffsetDateTime.now());
        Product savedProduct = repository.save(product);

        auditLogService.log("UPDATE", "PRODUCTOS", "Product", savedProduct.getId().toString(), "Producto actualizado: " + savedProduct.getName());

        Integer stock = inventoryRepository.findByProductId(id)
                .map(Inventory::getQuantityOnHand)
                .orElse(0);

        return mapper.toProductResponse(savedProduct, stock);
    }

    @Override
    @Transactional
    public ProductResponse changeSalePrice(Long id, ChangeSalePriceRequest request) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Producto no encontrado con id: " + id));

        if (Boolean.FALSE.equals(product.getIsActive())) {
            throw new GlobalException(HttpStatus.CONFLICT, "No se puede actualizar un producto inactivo");
        }

        product.setSalePrice(request.salePrice());
        product.setUpdatedAt(OffsetDateTime.now());
        Product savedProduct = repository.save(product);

        Integer stock = inventoryRepository.findByProductId(id)
                .map(Inventory::getQuantityOnHand)
                .orElse(0);

        return mapper.toProductResponse(savedProduct, stock);
    }

    @Override
    @Transactional
    public ProductResponse changeMinStock(Long id, ChangeMinStockRequest request) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Producto no encontrado con id: " + id));

        if (Boolean.FALSE.equals(product.getIsActive())) {
            throw new GlobalException(HttpStatus.CONFLICT, "No se puede actualizar un producto inactivo");
        }

        product.setMinStockLevel(request.minStockLevel());
        product.setUpdatedAt(OffsetDateTime.now());
        Product savedProduct = repository.save(product);

        Integer stock = inventoryRepository.findByProductId(id)
                .map(Inventory::getQuantityOnHand)
                .orElse(0);

        return mapper.toProductResponse(savedProduct, stock);
    }

    @Override
    @Transactional
    public ProductResponse changeCategory(Long id, ChangeCategoryRequest request) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Producto no encontrado con id: " + id));

        if (Boolean.FALSE.equals(product.getIsActive())) {
            throw new GlobalException(HttpStatus.CONFLICT, "No se puede actualizar un producto inactivo");
        }

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Categoría no encontrada con id: " + request.categoryId()));

        if (Boolean.FALSE.equals(category.getIsActive())) {
            throw new GlobalException(HttpStatus.CONFLICT, "No se puede asociar una categoría inactiva");
        }

        product.setCategory(category);
        product.setUpdatedAt(OffsetDateTime.now());
        Product savedProduct = repository.save(product);

        Integer stock = inventoryRepository.findByProductId(id)
                .map(Inventory::getQuantityOnHand)
                .orElse(0);

        return mapper.toProductResponse(savedProduct, stock);
    }

    @Override
    @Transactional
    public ProductResponse updateStatus(Long id, boolean active) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Producto no encontrado con id: " + id));

        if (product.getIsActive() == active) {
            throw new GlobalException(HttpStatus.CONFLICT, "El producto ya se encuentra " + (active ? "activo" : "inactivo"));
        }

        product.setIsActive(active);
        product.setUpdatedAt(OffsetDateTime.now());
        Product savedProduct = repository.save(product);

        auditLogService.log("UPDATE", "PRODUCTOS", "Product", savedProduct.getId().toString(), "Estado de producto " + savedProduct.getName() + " cambiado a " + (active ? "activo" : "inactivo"));

        Integer stock = inventoryRepository.findByProductId(id)
                .map(Inventory::getQuantityOnHand)
                .orElse(0);

        return mapper.toProductResponse(savedProduct, stock);
    }

    @Override
    @Transactional
    public ProductResponse adjustStock(Long id, ManualStockAdjustmentRequest request) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Producto no encontrado con id: " + id));

        if (Boolean.FALSE.equals(product.getIsActive())) {
            throw new GlobalException(HttpStatus.CONFLICT, "No se puede modificar el stock de un producto inactivo");
        }

        if (request.quantity() == 0) {
            throw new GlobalException(HttpStatus.BAD_REQUEST, "La cantidad del ajuste no puede ser cero");
        }

        Inventory inventory = inventoryRepository.findByProductId(id)
                .orElseGet(() -> {
                    Inventory newInv = new Inventory();
                    newInv.setProduct(product);
                    newInv.setQuantityOnHand(0);
                    newInv.setUpdatedAt(OffsetDateTime.now());
                    return inventoryRepository.save(newInv);
                });

        int newBalance = inventory.getQuantityOnHand() + request.quantity();
        if (newBalance < 0) {
            throw new GlobalException(HttpStatus.BAD_REQUEST,
                    "El ajuste dejaría el stock en negativo. Stock actual: " + inventory.getQuantityOnHand() + ", ajuste solicitado: " + request.quantity());
        }

        inventory.setQuantityOnHand(newBalance);
        inventory.setUpdatedAt(OffsetDateTime.now());
        inventoryRepository.save(inventory);

        User currentUser = getCurrentUser();

        InventoryMovement movement = new InventoryMovement();
        movement.setProduct(product);
        movement.setUser(currentUser);
        movement.setMovementType("AJUSTE_MANUAL");
        movement.setQuantity(request.quantity());
        movement.setBalanceAfter(newBalance);
        movement.setReferenceType("MANUAL");
        movement.setNotes(request.notes().trim());
        movement.setCreatedAt(OffsetDateTime.now());
        inventoryMovementRepository.save(movement);

        auditLogService.log(currentUser, "UPDATE", "INVENTARIO", "Product", product.getId().toString(), "Ajuste de inventario para " + product.getName() + ": " + (request.quantity() > 0 ? "+" : "") + request.quantity() + " unidades");

        return mapper.toProductResponse(product, newBalance);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Producto no encontrado con id: " + id));

        if (Boolean.FALSE.equals(product.getIsActive())) {
            throw new GlobalException(HttpStatus.CONFLICT, "El producto ya se encuentra inactivo");
        }

        product.setIsActive(false);
        product.setUpdatedAt(OffsetDateTime.now());
        repository.save(product);
        auditLogService.log("DELETE", "PRODUCTOS", "Product", product.getId().toString(), "Producto desactivado: " + product.getName());
    }

    private PageResponse<ProductResponse> toPageResponse(Page<Product> page) {
        if (page.isEmpty()) {
            return PageResponse.from(page, p -> mapper.toProductResponse(p, 0));
        }

        List<Long> productIds = page.getContent().stream().map(Product::getId).toList();
        Map<Long, Integer> stockMap = inventoryRepository.findByProductIdIn(productIds)
                .stream()
                .collect(Collectors.toMap(i -> i.getProduct().getId(), Inventory::getQuantityOnHand));

        return PageResponse.from(page, p -> mapper.toProductResponse(p, stockMap.getOrDefault(p.getId(), 0)));
    }

    private boolean callerIsAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) return false;
        return auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ADMINISTRATION") || a.getAuthority().equals("ROLE_ADMINISTRATION"));
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
