package com.zup.casadocodigo.shared.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PurchaseRequestValidator.class)
public @interface PurchaseRequest {
    String message() default "{purchase.document.required}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
