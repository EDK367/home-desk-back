package com.solairy.homedesk.inventory.dto.response;

import java.math.BigDecimal;

public record InventoryMovementLotResponse(
        Long id,
        Long movementId,
        InventoryLotResponse lot,
        Integer quantity,
        BigDecimal unitCost
) {
}
