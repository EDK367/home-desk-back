package com.solairy.homedesk.payment.service.impl;

import com.solairy.homedesk.common.exception.GlobalException;
import com.solairy.homedesk.common.response.PageResponse;
import com.solairy.homedesk.payment.dto.request.SalePaymentRequest;
import com.solairy.homedesk.payment.dto.response.SalePaymentResponse;
import com.solairy.homedesk.payment.mapper.SalePaymentMapper;
import com.solairy.homedesk.payment.model.PaymentMethod;
import com.solairy.homedesk.payment.model.SalePayment;
import com.solairy.homedesk.payment.repository.PaymentMethodRepository;
import com.solairy.homedesk.payment.repository.SalePaymentRepository;
import com.solairy.homedesk.payment.service.SalePaymentService;
import com.solairy.homedesk.sales.model.Sale;
import com.solairy.homedesk.sales.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalePaymentServiceImpl implements SalePaymentService {

    private final SalePaymentRepository salePaymentRepository;
    private final SaleRepository saleRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final SalePaymentMapper salePaymentMapper;

    @Override
    @Transactional(readOnly = true)
    public PageResponse<SalePaymentResponse> findBySale(Long saleId, Pageable pageable) {
        if (!saleRepository.existsById(saleId)) {
            throw new GlobalException(HttpStatus.NOT_FOUND, "Venta no encontrada con id: " + saleId);
        }
        Page<SalePayment> page = salePaymentRepository.findBySaleId(saleId, pageable);
        return PageResponse.from(page, salePaymentMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SalePaymentResponse> findBySaleId(Long saleId) {
        if (!saleRepository.existsById(saleId)) {
            throw new GlobalException(HttpStatus.NOT_FOUND, "Venta no encontrada con id: " + saleId);
        }
        return salePaymentRepository.findBySaleId(saleId)
                .stream()
                .map(salePaymentMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SalePaymentResponse findById(Long id) {
        SalePayment payment = salePaymentRepository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Pago no encontrado con id: " + id));
        return salePaymentMapper.toResponse(payment);
    }

    @Override
    @Transactional
    public SalePaymentResponse create(SalePaymentRequest request) {
        if (request.saleId() == null) {
            throw new GlobalException(HttpStatus.BAD_REQUEST, "El ID de la venta es obligatorio");
        }

        Sale sale = saleRepository.findById(request.saleId())
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Venta no encontrada con id: " + request.saleId()));

        if ("CANCELADA".equalsIgnoreCase(sale.getStatus().getName())) {
            throw new GlobalException(HttpStatus.CONFLICT, "No se puede registrar un pago a una venta cancelada");
        }

        PaymentMethod paymentMethod = paymentMethodRepository.findById(request.paymentMethodId())
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Método de pago no encontrado con id: " + request.paymentMethodId()));

        if (Boolean.FALSE.equals(paymentMethod.getIsActive())) {
            throw new GlobalException(HttpStatus.CONFLICT, "No se puede utilizar un método de pago inactivo");
        }

        if (request.amount() == null || request.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new GlobalException(HttpStatus.BAD_REQUEST, "El monto del pago debe ser mayor a 0");
        }

        SalePayment payment = new SalePayment();
        payment.setSale(sale);
        payment.setPaymentMethod(paymentMethod);
        payment.setAmount(request.amount());
        payment.setPaymentDate(request.paymentDate() != null ? request.paymentDate() : OffsetDateTime.now());
        payment.setReferenceNumber(request.referenceNumber() != null ? request.referenceNumber().trim() : null);
        payment.setNotes(request.notes() != null ? request.notes().trim() : null);
        payment.setCreatedAt(OffsetDateTime.now());

        SalePayment saved = salePaymentRepository.save(payment);
        return salePaymentMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        SalePayment payment = salePaymentRepository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Pago no encontrado con id: " + id));
        salePaymentRepository.delete(payment);
    }
}
