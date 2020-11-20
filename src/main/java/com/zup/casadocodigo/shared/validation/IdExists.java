package com.zup.casadocodigo.shared.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdExistsValidator.class)
public @interface IdExists {
    String message() default "{idNotExists}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};

    Class<?> entityClass();
    String fieldName();
}
