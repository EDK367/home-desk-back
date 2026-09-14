package com.solairy.homedesk.sales.mapper;

import com.solairy.homedesk.sales.dto.response.CustomerResponse;
import com.solairy.homedesk.sales.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerResponse toCustomerResponse(Customer customer);
}
