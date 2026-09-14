package com.solairy.homedesk.suppliers.dto.response;

import java.time.OffsetDateTime;
import java.util.List;

public record SupplierResponse(
        Long id,
        String name,
        String taxNumber,
        String contactName,
        List<PhoneNumberResponse> phones,
        String email,
        String address,
        Boolean isActive,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}