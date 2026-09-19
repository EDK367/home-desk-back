package com.solairy.homedesk.payment.service.impl;

import com.solairy.homedesk.common.exception.GlobalException;
import com.solairy.homedesk.common.response.PageResponse;
import com.solairy.homedesk.payment.dto.request.PaymentMethodRequest;
import com.solairy.homedesk.payment.dto.response.PaymentMethodResponse;
import com.solairy.homedesk.payment.mapper.PaymentMethodMapper;
import com.solairy.homedesk.payment.model.PaymentMethod;
import com.solairy.homedesk.payment.repository.PaymentMethodRepository;
import com.solairy.homedesk.payment.service.PaymentMethodService;
import com.solairy.homedesk.audit.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentMethodMapper paymentMethodMapper;
    private final AuditLogService auditLogService;

    @Override
    @Transactional(readOnly = true)
    public PageResponse<PaymentMethodResponse> findAllSales(Pageable pageable) {
        Page<PaymentMethod> page = paymentMethodRepository.findAllByIsActive(true, pageable);
        return PageResponse.from(page, paymentMethodMapper::toPaymentMethodResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<PaymentMethodResponse> findAllAdmin(Pageable pageable) {
        Page<PaymentMethod> page = paymentMethodRepository.findAll(pageable);
        return PageResponse.from(page, paymentMethodMapper::toPaymentMethodResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentMethodResponse findById(Long id) {
        PaymentMethod method = paymentMethodRepository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Método de pago no encontrado con id: " + id));
        return paymentMethodMapper.toPaymentMethodResponse(method);
    }

    @Override
    @Transactional
    public PaymentMethodResponse create(PaymentMethodRequest request) {
        String name = request.name().trim();
        if (paymentMethodRepository.existsByName(name)) {
            throw new GlobalException(HttpStatus.CONFLICT, "Ya existe un método de pago con el nombre: " + name);
        }

        PaymentMethod method = new PaymentMethod();
        method.setName(name);
        method.setDescription(request.description() != null ? request.description().trim() : null);
        method.setIsActive(true);
        method.setCreatedAt(OffsetDateTime.now());

        PaymentMethod saved = paymentMethodRepository.save(method);
        auditLogService.log("CREATE", "METODOS_PAGO", "PaymentMethod", saved.getId().toString(), "Método de pago creado: " + saved.getName());
        return paymentMethodMapper.toPaymentMethodResponse(saved);
    }

    @Override
    @Transactional
    public PaymentMethodResponse update(Long id, PaymentMethodRequest request) {
        PaymentMethod method = paymentMethodRepository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Método de pago no encontrado con id: " + id));

        if (Boolean.FALSE.equals(method.getIsActive())) {
            throw new GlobalException(HttpStatus.CONFLICT, "No se puede actualizar un método de pago inactivo");
        }

        String name = request.name().trim();
        if (paymentMethodRepository.existsByNameAndIdNot(name, id)) {
            throw new GlobalException(HttpStatus.CONFLICT, "Ya existe un método de pago con el nombre: " + name);
        }

        method.setName(name);
        method.setDescription(request.description() != null ? request.description().trim() : null);

        PaymentMethod saved = paymentMethodRepository.save(method);
        auditLogService.log("UPDATE", "METODOS_PAGO", "PaymentMethod", saved.getId().toString(), "Método de pago actualizado: " + saved.getName());
        return paymentMethodMapper.toPaymentMethodResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        PaymentMethod method = paymentMethodRepository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Método de pago no encontrado con id: " + id));

        if (Boolean.FALSE.equals(method.getIsActive())) {
            throw new GlobalException(HttpStatus.CONFLICT, "El método de pago ya se encuentra inactivo");
        }

        method.setIsActive(false);
        paymentMethodRepository.save(method);
        auditLogService.log("DELETE", "METODOS_PAGO", "PaymentMethod", method.getId().toString(), "Método de pago desactivado: " + method.getName());
    }
}
