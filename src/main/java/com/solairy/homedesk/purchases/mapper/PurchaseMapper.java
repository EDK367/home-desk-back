package com.solairy.homedesk.purchases.mapper;

import com.solairy.homedesk.auth.mapper.UserMapper;
import com.solairy.homedesk.purchases.dto.response.PurchaseResponse;
import com.solairy.homedesk.purchases.model.Purchase;
import com.solairy.homedesk.purchases.model.PurchaseItem;
import com.solairy.homedesk.suppliers.mapper.SupplierMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        SupplierMapper.class,
        UserMapper.class,
        PurchaseStatusMapper.class,
        PurchaseItemMapper.class
})
public interface PurchaseMapper {

    @Mapping(target = "items", source = "items")
    PurchaseResponse toResponse(Purchase purchase, List<PurchaseItem> items);

    @Mapping(target = "items", ignore = true)
    PurchaseResponse toResponse(Purchase purchase);
}
