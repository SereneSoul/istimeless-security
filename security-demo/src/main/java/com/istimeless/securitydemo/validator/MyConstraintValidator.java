package com.istimeless.securitydemo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author lijiayin
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {
    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        System.out.println("My validator init");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        System.out.println(value);
        return true;
    }
}
