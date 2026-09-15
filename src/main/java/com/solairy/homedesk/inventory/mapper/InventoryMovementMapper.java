package com.solairy.homedesk.inventory.mapper;

import com.solairy.homedesk.auth.mapper.UserMapper;
import com.solairy.homedesk.catalog.mapper.ProductMapper;
import com.solairy.homedesk.inventory.dto.response.InventoryMovementResponse;
import com.solairy.homedesk.inventory.model.InventoryMovement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, UserMapper.class})
public interface InventoryMovementMapper {

    @Mapping(target = "product", source = "product")
    @Mapping(target = "user", source = "user")
    InventoryMovementResponse toResponse(InventoryMovement movement);
}
