package com.solairy.homedesk.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NoDuplicatePhonesValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NoDuplicatePhones {

    String message() default "La lista de teléfonos contiene números duplicados";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
