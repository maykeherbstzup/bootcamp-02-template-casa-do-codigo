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
    private String className;
    private String fieldName;

    @PersistenceContext
    EntityManager em;

    @Override
    public void initialize(Unique constraintAnnotation) {
        this.className = constraintAnnotation.className();
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(Object fieldValue, ConstraintValidatorContext context) {
        Query query = em.createQuery(this.getQuery());
        query.setParameter("fieldValue", fieldValue);

        List<?> results = query.getResultList();

        return results.size() == 0;
    }

    private String getQuery() {
        return "SELECT 1 FROM " + this.className + " WHERE " + this.fieldName + " = :fieldValue ";
    }
}
