package com.solairy.homedesk.suppliers.mapper;

import com.solairy.homedesk.suppliers.dto.response.PhoneNumberResponse;
import com.solairy.homedesk.suppliers.dto.response.SupplierResponse;
import com.solairy.homedesk.suppliers.model.PhoneSupplier;
import com.solairy.homedesk.suppliers.model.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    @Mapping(target = "phone", source = "phone.phone")
    PhoneNumberResponse toPhoneResponse(PhoneSupplier phoneSupplier);

    @Mapping(target = "phones", source = "phones")
    SupplierResponse toSupplierResponse(Supplier supplier, List<PhoneSupplier> phones);

    @Mapping(target = "phones", ignore = true)
    SupplierResponse toSupplierResponse(Supplier supplier);
}

