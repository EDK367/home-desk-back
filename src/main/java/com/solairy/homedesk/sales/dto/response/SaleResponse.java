package com.solairy.homedesk.sales.dto.response;

import com.solairy.homedesk.auth.dto.response.UserResponse;
import com.solairy.homedesk.payment.dto.response.SalePaymentResponse;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public record SaleResponse(
        Long id,
        CustomerResponse customer,
        UserResponse user,
        SaleStatusResponse status,
        String invoiceNumber,
        OffsetDateTime saleDate,
        BigDecimal subtotal,
        BigDecimal taxAmount,
        BigDecimal totalAmount,
        String invoicePdfPath,
        List<SaleItemResponse> items,
        List<SalePaymentResponse> payments,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
