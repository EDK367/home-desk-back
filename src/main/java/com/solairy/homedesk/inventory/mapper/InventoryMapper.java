package com.solairy.homedesk.inventory.mapper;

import com.solairy.homedesk.catalog.mapper.ProductMapper;
import com.solairy.homedesk.inventory.dto.response.InventoryResponse;
import com.solairy.homedesk.inventory.model.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface InventoryMapper {

    @Mapping(target = "product", source = "product")
    @Mapping(target = "isLowStock", expression = "java(inventory.getProduct() != null && inventory.getProduct().getMinStockLevel() != null && inventory.getQuantityOnHand() <= inventory.getProduct().getMinStockLevel())")
    InventoryResponse toResponse(Inventory inventory);
}
