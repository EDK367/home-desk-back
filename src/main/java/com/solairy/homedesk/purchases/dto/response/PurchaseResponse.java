package com.solairy.homedesk.purchases.dto.response;

import com.solairy.homedesk.auth.dto.response.UserResponse;
import com.solairy.homedesk.suppliers.dto.response.SupplierResponse;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public record PurchaseResponse(
        Long id,
        SupplierResponse supplier,
        UserResponse user,
        PurchaseStatusResponse status,
        OffsetDateTime purchaseDate,
        BigDecimal subtotal,
        BigDecimal taxAmount,
        BigDecimal totalAmount,
        String notes,
        List<PurchaseItemResponse> items,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
