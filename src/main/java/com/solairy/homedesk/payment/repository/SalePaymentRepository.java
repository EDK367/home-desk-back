package com.solairy.homedesk.payment.repository;

import com.solairy.homedesk.payment.model.SalePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalePaymentRepository extends JpaRepository<SalePayment, Long> {
}
