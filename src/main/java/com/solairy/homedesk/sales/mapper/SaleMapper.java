package com.solairy.homedesk.sales.mapper;

import com.solairy.homedesk.auth.mapper.UserMapper;
import com.solairy.homedesk.payment.dto.response.SalePaymentResponse;
import com.solairy.homedesk.payment.mapper.SalePaymentMapper;
import com.solairy.homedesk.payment.model.SalePayment;
import com.solairy.homedesk.sales.dto.response.SaleResponse;
import com.solairy.homedesk.sales.model.Sale;
import com.solairy.homedesk.sales.model.SaleItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        CustomerMapper.class,
        UserMapper.class,
        SaleStatusMapper.class,
        SaleItemMapper.class,
        SalePaymentMapper.class
})
public interface SaleMapper {

    @Mapping(target = "items", source = "items")
    @Mapping(target = "payments", source = "payments")
    SaleResponse toResponse(Sale sale, List<SaleItem> items, List<SalePayment> payments);

    @Mapping(target = "items", ignore = true)
    @Mapping(target = "payments", ignore = true)
    SaleResponse toResponse(Sale sale);
}
