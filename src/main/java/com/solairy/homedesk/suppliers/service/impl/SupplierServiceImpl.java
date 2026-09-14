package com.solairy.homedesk.suppliers.service.impl;

import com.solairy.homedesk.common.exception.GlobalException;
import com.solairy.homedesk.common.response.PageResponse;
import com.solairy.homedesk.suppliers.dto.request.SupplierRequest;
import com.solairy.homedesk.suppliers.mapper.SupplierMapper;
import com.solairy.homedesk.suppliers.dto.response.SupplierResponse;
import com.solairy.homedesk.suppliers.model.PhoneNumber;
import com.solairy.homedesk.suppliers.model.PhoneSupplier;
import com.solairy.homedesk.suppliers.model.Supplier;
import com.solairy.homedesk.suppliers.repository.PhoneNumberRepository;
import com.solairy.homedesk.suppliers.repository.PhoneSupplierRepository;
import com.solairy.homedesk.suppliers.repository.SupplierRepository;
import com.solairy.homedesk.suppliers.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository repository;
    private final PhoneNumberRepository phoneNumberRepository;
    private final PhoneSupplierRepository phoneSupplierRepository;
    private final SupplierMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public PageResponse<SupplierResponse> findAll(Pageable pageable, boolean includeInactive) {
        Page<Supplier> page;
        if (includeInactive) {
            page = repository.findAll(pageable);
            return PageResponse.from(page, this::toResponse);
        }
        page = repository.findAllByIsActive(true, pageable);
        return PageResponse.from(page, this::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierResponse findById(Long id) {
        Supplier supplier = repository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Proveedor no encontrado con id: " + id));
        return toResponse(supplier);
    }

    @Override
    @Transactional
    public SupplierResponse create(SupplierRequest request) {
        if (repository.existsByTaxNumber(request.taxNumber())) {
            throw new GlobalException(HttpStatus.CONFLICT,
                    "Ya existe un proveedor con el número tributario: " + request.taxNumber());
        }

        Supplier supplier = new Supplier();
        supplier.setName(request.name());
        supplier.setTaxNumber(request.taxNumber());
        supplier.setContactName(request.contactName());
        supplier.setEmail(request.email());
        supplier.setAddress(request.address());
        supplier.setIsActive(true);
        supplier.setCreatedAt(OffsetDateTime.now());
        supplier.setUpdatedAt(OffsetDateTime.now());

        Supplier saved = repository.save(supplier);
        associatePhones(saved, request);

        return toResponse(saved);
    }

    @Override
    @Transactional
    public SupplierResponse update(Long id, SupplierRequest request) {
        Supplier supplier = repository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Proveedor no encontrado con id: " + id));

        if (repository.existsByTaxNumberAndIdNot(request.taxNumber(), id)) {
            throw new GlobalException(HttpStatus.CONFLICT,
                    "Ya existe un proveedor con el número tributario: " + request.taxNumber());
        }

        supplier.setName(request.name());
        supplier.setTaxNumber(request.taxNumber());
        supplier.setContactName(request.contactName());
        supplier.setEmail(request.email());
        supplier.setAddress(request.address());
        supplier.setUpdatedAt(OffsetDateTime.now());

        phoneSupplierRepository.deleteBySupplier(supplier);
        associatePhones(supplier, request);

        return toResponse(repository.save(supplier));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Supplier supplier = repository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Proveedor no encontrado con id: " + id));
        if (Boolean.FALSE.equals(supplier.getIsActive())) {
            throw new GlobalException(HttpStatus.CONFLICT, "El proveedor ya se encuentra inactivo");
        }
        supplier.setIsActive(false);
        supplier.setUpdatedAt(OffsetDateTime.now());
        repository.save(supplier);
    }


    private SupplierResponse toResponse(Supplier supplier) {
        List<PhoneSupplier> phones = phoneSupplierRepository.findBySupplier(supplier);
        return mapper.toSupplierResponse(supplier, phones);
    }

    private void associatePhones(Supplier supplier, SupplierRequest request) {
        request.phones().forEach(phoneRequest -> {
            PhoneNumber phoneNumber = phoneNumberRepository.findByPhone(phoneRequest.phone())
                    .orElseGet(() -> {
                        PhoneNumber newPhone = new PhoneNumber();
                        newPhone.setPhone(phoneRequest.phone());
                        return phoneNumberRepository.save(newPhone);
                    });

            PhoneSupplier phoneSupplier = new PhoneSupplier();
            phoneSupplier.setSupplier(supplier);
            phoneSupplier.setPhone(phoneNumber);
            phoneSupplierRepository.save(phoneSupplier);
        });
    }

}
