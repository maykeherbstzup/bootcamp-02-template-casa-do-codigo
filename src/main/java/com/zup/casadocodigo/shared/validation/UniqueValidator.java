package com.zup.casadocodigo.shared.validation;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@Component
public class UniqueValidator implements ConstraintValidator<Unique, Object> {
    private Class<?> entityClass;
    private String fieldName;

    @PersistenceContext
    EntityManager em;

    @Override
    public void initialize(Unique constraintAnnotation) {
        this.entityClass = constraintAnnotation.entityClass();
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(Object fieldValue, ConstraintValidatorContext context) {
        Query query = em.createQuery(this.getQuery());
        query.setParameter("fieldValue", fieldValue);

        List<?> result = query.getResultList();

        return result.isEmpty();
    }

    private String getQuery() {
        return "SELECT 1 FROM " + this.entityClass.getName() + " WHERE " + this.fieldName + " = :fieldValue ";
    }
}
