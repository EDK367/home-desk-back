package com.solairy.homedesk.suppliers.mapper;

import com.solairy.homedesk.catalog.mapper.ProductMapper;
import com.solairy.homedesk.suppliers.dto.response.SupplierProductResponse;
import com.solairy.homedesk.suppliers.model.SupplierProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SupplierMapper.class, ProductMapper.class})
public interface SupplierProductMapper {

    @Mapping(target = "supplier", source = "supplier")
    @Mapping(target = "product", source = "product")
    SupplierProductResponse toResponse(SupplierProduct supplierProduct);
}
