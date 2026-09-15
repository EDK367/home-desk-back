package com.solairy.homedesk.payment.mapper;

import com.solairy.homedesk.payment.dto.response.SalePaymentResponse;
import com.solairy.homedesk.payment.model.SalePayment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PaymentMethodMapper.class})
public interface SalePaymentMapper {

    @Mapping(target = "saleId", source = "sale.id")
    @Mapping(target = "paymentMethod", source = "paymentMethod")
    SalePaymentResponse toResponse(SalePayment salePayment);
}
