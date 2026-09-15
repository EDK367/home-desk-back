package com.solairy.homedesk.suppliers.service.impl;

import com.solairy.homedesk.catalog.model.Product;
import com.solairy.homedesk.catalog.repository.ProductRepository;
import com.solairy.homedesk.common.exception.GlobalException;
import com.solairy.homedesk.common.response.PageResponse;
import com.solairy.homedesk.suppliers.dto.request.SupplierProductRequest;
import com.solairy.homedesk.suppliers.dto.request.SupplierProductUpdateRequest;
import com.solairy.homedesk.suppliers.dto.response.SupplierProductResponse;
import com.solairy.homedesk.suppliers.mapper.SupplierProductMapper;
import com.solairy.homedesk.suppliers.model.Supplier;
import com.solairy.homedesk.suppliers.model.SupplierProduct;
import com.solairy.homedesk.suppliers.repository.SupplierProductRepository;
import com.solairy.homedesk.suppliers.repository.SupplierRepository;
import com.solairy.homedesk.suppliers.service.SupplierProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierProductServiceImpl implements SupplierProductService {

    private final SupplierProductRepository repository;
    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;
    private final SupplierProductMapper supplierProductMapper;

    @Override
    @Transactional(readOnly = true)
    public PageResponse<SupplierProductResponse> findAll(Pageable pageable, Long supplierId, Long productId) {
        Page<SupplierProduct> page;
        if (supplierId != null && productId != null) {
            List<SupplierProduct> list = repository.findBySupplierIdAndProductId(supplierId, productId)
                    .map(List::of)
                    .orElseGet(List::of);
            page = new PageImpl<>(list, pageable, list.size());
        } else if (supplierId != null) {
            page = repository.findBySupplierId(supplierId, pageable);
        } else if (productId != null) {
            page = repository.findByProductId(productId, pageable);
        } else {
            page = repository.findAll(pageable);
        }
        return PageResponse.from(page, supplierProductMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<SupplierProductResponse> findBySupplier(Long supplierId, Pageable pageable) {
        if (!supplierRepository.existsById(supplierId)) {
            throw new GlobalException(HttpStatus.NOT_FOUND, "Proveedor no encontrado con id: " + supplierId);
        }
        Page<SupplierProduct> page = repository.findBySupplierId(supplierId, pageable);
        return PageResponse.from(page, supplierProductMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<SupplierProductResponse> findByProduct(Long productId, Pageable pageable) {
        if (!productRepository.existsById(productId)) {
            throw new GlobalException(HttpStatus.NOT_FOUND, "Producto no encontrado con id: " + productId);
        }
        Page<SupplierProduct> page = repository.findByProductId(productId, pageable);
        return PageResponse.from(page, supplierProductMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierProductResponse findById(Long id) {
        SupplierProduct supplierProduct = repository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Relación proveedor-producto no encontrada con id: " + id));
        return supplierProductMapper.toResponse(supplierProduct);
    }

    @Override
    @Transactional
    public SupplierProductResponse create(SupplierProductRequest request) {
        Supplier supplier = supplierRepository.findById(request.supplierId())
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Proveedor no encontrado con id: " + request.supplierId()));

        if (Boolean.FALSE.equals(supplier.getIsActive())) {
            throw new GlobalException(HttpStatus.CONFLICT, "No se puede asociar un producto a un proveedor inactivo");
        }

        Product product = productRepository.findById(request.productId())
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Producto no encontrado con id: " + request.productId()));

        if (Boolean.FALSE.equals(product.getIsActive())) {
            throw new GlobalException(HttpStatus.CONFLICT, "No se puede asociar un producto inactivo al proveedor");
        }

        if (repository.existsBySupplierIdAndProductId(request.supplierId(), request.productId())) {
            throw new GlobalException(HttpStatus.CONFLICT, "El producto ya se encuentra asociado a este proveedor");
        }

        SupplierProduct supplierProduct = new SupplierProduct();
        supplierProduct.setSupplier(supplier);
        supplierProduct.setProduct(product);
        supplierProduct.setSupplierSku(request.supplierSku() != null ? request.supplierSku().trim() : null);
        supplierProduct.setCost(request.cost());
        supplierProduct.setCreatedAt(OffsetDateTime.now());
        supplierProduct.setUpdatedAt(OffsetDateTime.now());

        SupplierProduct saved = repository.save(supplierProduct);
        return supplierProductMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public SupplierProductResponse update(Long id, SupplierProductUpdateRequest request) {
        SupplierProduct supplierProduct = repository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Relación proveedor-producto no encontrada con id: " + id));

        supplierProduct.setSupplierSku(request.supplierSku() != null ? request.supplierSku().trim() : null);
        supplierProduct.setCost(request.cost());
        supplierProduct.setUpdatedAt(OffsetDateTime.now());

        SupplierProduct saved = repository.save(supplierProduct);
        return supplierProductMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        SupplierProduct supplierProduct = repository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Relación proveedor-producto no encontrada con id: " + id));
        repository.delete(supplierProduct);
    }

    @Override
    @Transactional
    public void deleteBySupplierAndProduct(Long supplierId, Long productId) {
        SupplierProduct supplierProduct = repository.findBySupplierIdAndProductId(supplierId, productId)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Relación proveedor-producto no encontrada"));
        repository.delete(supplierProduct);
    }
}
