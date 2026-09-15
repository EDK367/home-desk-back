package com.solairy.homedesk.inventory.dto.response;

import com.solairy.homedesk.auth.dto.response.UserResponse;
import com.solairy.homedesk.catalog.dto.response.ProductResponse;

import java.time.OffsetDateTime;

public record InventoryMovementResponse(
        Long id,
        ProductResponse product,
        UserResponse user,
        String movementType,
        Integer quantity,
        Integer balanceAfter,
        String referenceType,
        Long referenceId,
        String costingMethod,
        String notes,
        OffsetDateTime createdAt
) {
}
