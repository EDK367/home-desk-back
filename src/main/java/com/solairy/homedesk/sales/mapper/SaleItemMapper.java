package com.solairy.homedesk.sales.mapper;

import com.solairy.homedesk.catalog.mapper.ProductMapper;
import com.solairy.homedesk.sales.dto.response.SaleItemResponse;
import com.solairy.homedesk.sales.model.SaleItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface SaleItemMapper {

    @Mapping(target = "product", source = "product")
    SaleItemResponse toResponse(SaleItem item);
}
