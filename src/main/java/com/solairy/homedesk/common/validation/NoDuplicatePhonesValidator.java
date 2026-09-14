package com.solairy.homedesk.common.validation;

import com.solairy.homedesk.suppliers.dto.request.PhoneNumberRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class NoDuplicatePhonesValidator
        implements ConstraintValidator<NoDuplicatePhones, List<PhoneNumberRequest>> {

    @Override
    public boolean isValid(List<PhoneNumberRequest> phones, ConstraintValidatorContext context) {
        if (phones == null || phones.isEmpty()) {
            return true;
        }

        List<String> numbers = phones.stream()
                .map(PhoneNumberRequest::phone)
                .filter(p -> p != null && !p.isBlank())
                .toList();

        Set<String> unique = numbers.stream().collect(Collectors.toSet());

        return unique.size() == numbers.size();
    }
}
