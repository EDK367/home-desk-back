package com.solairy.homedesk.inventory.mapper;

import com.solairy.homedesk.catalog.mapper.ProductMapper;
import com.solairy.homedesk.inventory.dto.response.InventoryLotResponse;
import com.solairy.homedesk.inventory.model.InventoryLot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface InventoryLotMapper {

    @Mapping(target = "product", source = "product")
    @Mapping(target = "purchaseItemId", source = "purchaseItem.id")
    @Mapping(target = "purchaseId", source = "purchaseItem.purchase.id")
    InventoryLotResponse toResponse(InventoryLot lot);
}
