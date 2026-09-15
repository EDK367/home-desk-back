package com.solairy.homedesk.purchases.mapper;

import com.solairy.homedesk.catalog.mapper.ProductMapper;
import com.solairy.homedesk.purchases.dto.response.PurchaseItemResponse;
import com.solairy.homedesk.purchases.model.PurchaseItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface PurchaseItemMapper {

    @Mapping(target = "product", source = "product")
    PurchaseItemResponse toResponse(PurchaseItem item);
}
