package com.solairy.homedesk.payment.repository;

import com.solairy.homedesk.payment.model.SalePayment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface SalePaymentRepository extends JpaRepository<SalePayment, Long> {

    List<SalePayment> findBySaleId(Long saleId);

    Page<SalePayment> findBySaleId(Long saleId, Pageable pageable);

    Page<SalePayment> findByPaymentMethodId(Long paymentMethodId, Pageable pageable);

    Page<SalePayment> findByPaymentDateBetween(OffsetDateTime start, OffsetDateTime end, Pageable pageable);
}
