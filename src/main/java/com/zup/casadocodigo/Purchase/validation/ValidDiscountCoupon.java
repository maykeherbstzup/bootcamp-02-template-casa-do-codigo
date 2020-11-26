package com.zup.casadocodigo.Purchase.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidDiscountCouponValidator.class)
public @interface ValidDiscountCoupon {
    String message() default "{discountCoupon.invalid}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

