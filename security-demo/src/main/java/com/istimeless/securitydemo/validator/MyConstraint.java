package com.istimeless.securitydemo.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author lijiayin
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyConstraint {
    String message();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
