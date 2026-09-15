package com.solairy.homedesk.inventory.mapper;

import com.solairy.homedesk.inventory.dto.response.InventoryMovementLotResponse;
import com.solairy.homedesk.inventory.model.InventoryMovementLot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {InventoryLotMapper.class})
public interface InventoryMovementLotMapper {

    @Mapping(target = "movementId", source = "movement.id")
    @Mapping(target = "lot", source = "lot")
    InventoryMovementLotResponse toResponse(InventoryMovementLot movementLot);
}
