package com.solairy.homedesk.sales.service.impl;

import com.solairy.homedesk.common.exception.GlobalException;
import com.solairy.homedesk.common.response.PageResponse;
import com.solairy.homedesk.sales.dto.request.CustomerRequest;
import com.solairy.homedesk.sales.dto.response.CustomerResponse;
import com.solairy.homedesk.sales.mapper.CustomerMapper;
import com.solairy.homedesk.sales.model.Customer;
import com.solairy.homedesk.sales.repository.CustomerRepository;
import com.solairy.homedesk.sales.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public PageResponse<CustomerResponse> findAllSales(Pageable pageable) {
        Page<Customer> page = repository.findAllByIsActive(true, pageable);
        return PageResponse.from(page, mapper::toCustomerResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<CustomerResponse> findAllAdmin(Pageable pageable) {
        Page<Customer> page = repository.findAll(pageable);
        return PageResponse.from(page, mapper::toCustomerResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponse findById(Long id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Cliente no encontrado con id: " + id));
        return mapper.toCustomerResponse(customer);
    }

    @Override
    @Transactional
    public CustomerResponse create(CustomerRequest request) {
        String taxNumber = request.taxNumber() != null ? request.taxNumber().trim() : null;
        if (taxNumber != null && !taxNumber.isBlank()) {
            if (repository.existsByTaxNumber(taxNumber)) {
                throw new GlobalException(HttpStatus.CONFLICT, "Ya existe un cliente con el número de identificación tributaria: " + taxNumber);
            }
        }

        Customer customer = new Customer();
        customer.setFullName(request.fullName().trim());
        customer.setTaxNumber(taxNumber != null && !taxNumber.isBlank() ? taxNumber : null);
        customer.setPhone(request.phone() != null ? request.phone().trim() : null);
        customer.setEmail(request.email() != null ? request.email().trim() : null);
        customer.setAddress(request.address() != null ? request.address().trim() : null);
        customer.setIsActive(true);
        customer.setCreatedAt(OffsetDateTime.now());
        customer.setUpdatedAt(OffsetDateTime.now());

        Customer saved = repository.save(customer);
        return mapper.toCustomerResponse(saved);
    }

    @Override
    @Transactional
    public CustomerResponse update(Long id, CustomerRequest request) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Cliente no encontrado con id: " + id));

        if (Boolean.FALSE.equals(customer.getIsActive())) {
            throw new GlobalException(HttpStatus.CONFLICT, "No se puede actualizar un cliente inactivo");
        }

        String taxNumber = request.taxNumber() != null ? request.taxNumber().trim() : null;
        if (taxNumber != null && !taxNumber.isBlank()) {
            if (repository.existsByTaxNumberAndIdNot(taxNumber, id)) {
                throw new GlobalException(HttpStatus.CONFLICT, "Ya existe un cliente con el número de identificación tributaria: " + taxNumber);
            }
        }

        customer.setFullName(request.fullName().trim());
        customer.setTaxNumber(taxNumber != null && !taxNumber.isBlank() ? taxNumber : null);
        customer.setPhone(request.phone() != null ? request.phone().trim() : null);
        customer.setEmail(request.email() != null ? request.email().trim() : null);
        customer.setAddress(request.address() != null ? request.address().trim() : null);
        customer.setUpdatedAt(OffsetDateTime.now());

        Customer saved = repository.save(customer);
        return mapper.toCustomerResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Cliente no encontrado con id: " + id));

        if (Boolean.FALSE.equals(customer.getIsActive())) {
            throw new GlobalException(HttpStatus.CONFLICT, "El cliente ya se encuentra inactivo");
        }

        customer.setIsActive(false);
        customer.setUpdatedAt(OffsetDateTime.now());
        repository.save(customer);
    }
}
