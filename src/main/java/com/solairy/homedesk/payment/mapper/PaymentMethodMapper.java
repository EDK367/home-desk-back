package com.solairy.homedesk.payment.mapper;

import com.solairy.homedesk.payment.dto.response.PaymentMethodResponse;
import com.solairy.homedesk.payment.model.PaymentMethod;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMethodMapper {

    PaymentMethodResponse toPaymentMethodResponse(PaymentMethod paymentMethod);
}
