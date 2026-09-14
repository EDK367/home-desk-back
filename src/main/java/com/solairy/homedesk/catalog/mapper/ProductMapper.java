package com.solairy.homedesk.catalog.mapper;

import com.solairy.homedesk.catalog.dto.response.ProductResponse;
import com.solairy.homedesk.catalog.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {

    @Mapping(target = "stock", source = "stock")
    ProductResponse toProductResponse(Product product, Integer stock);

    @Mapping(target = "stock", constant = "0")
    ProductResponse toProductResponse(Product product);
}
