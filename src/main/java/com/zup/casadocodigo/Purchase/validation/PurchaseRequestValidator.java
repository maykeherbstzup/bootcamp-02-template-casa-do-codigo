package com.zup.casadocodigo.Purchase.validation;

import com.zup.casadocodigo.Location.Country;
import com.zup.casadocodigo.Location.State;
import com.zup.casadocodigo.Purchase.NewPurchaseRequest;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

public class PurchaseRequestValidator implements ConstraintValidator<PurchaseRequest, NewPurchaseRequest> {
    @PersistenceContext
    EntityManager em;

    @Override
    public boolean isValid(NewPurchaseRequest newPurchaseRequest, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();

        return this.validateDocument(newPurchaseRequest, context) && this.validateState(newPurchaseRequest, context);
    }

    /**
     * Parâmetro 'stateId' é obrigatório quando o país possuir estado cadastrado
     */
    private boolean validateState(NewPurchaseRequest request, ConstraintValidatorContext context) {
        String countryId = request.getCountryId() != null && request.getCountryId().isBlank() ? null : request.getCountryId();
        String stateId = request.getStateId() != null && request.getStateId().isBlank() ? null : request.getStateId();

        if (countryId == null) {
            return true;
        }

        Country country = em.find(Country.class, UUID.fromString(countryId));

        if (stateId != null) {
            State state = em.find(State.class, UUID.fromString(stateId));

            if (!state.belongsToCountry(country)) {
                context.buildConstraintViolationWithTemplate("{purchase.state.notExistsInCountry}").addPropertyNode(
                        "stateId").addConstraintViolation();
                return false;
            }
        }

        if (stateId == null && !country.getStates().isEmpty()) {
            context.buildConstraintViolationWithTemplate("{purchase.state.required}").addPropertyNode("stateId").addConstraintViolation();
            return false;
        }

        return true;
    }

    /**
     * Paramêtro 'document' deve ser um CPF ou CNPJ válido
     */
    private boolean validateDocument(NewPurchaseRequest newPurchaseRequest, ConstraintValidatorContext context) {
        String document = newPurchaseRequest.getDocument() != null && newPurchaseRequest.getDocument().isBlank() ?
                null : newPurchaseRequest.getDocument();

        if (document == null) {
            return true;
        }

        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.initialize(null);

        CNPJValidator cnpjValidator = new CNPJValidator();
        cnpjValidator.initialize(null);

        boolean validCpf = cpfValidator.isValid(document, null);
        boolean validCnpj = cnpjValidator.isValid(document, null);

        if (!validCpf && !validCnpj) {
            context.buildConstraintViolationWithTemplate("{purchase.document.required}").addPropertyNode(
                    "document").addConstraintViolation();
            return false;
        }

        return true;
    }
}
