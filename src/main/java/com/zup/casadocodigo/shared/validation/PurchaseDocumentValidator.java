package com.zup.casadocodigo.shared.validation;

import com.zup.casadocodigo.Purchase.NewPurchaseRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PurchaseDocumentValidator implements ConstraintValidator<PurchaseDocument, NewPurchaseRequest> {
    @Override
    public boolean isValid(NewPurchaseRequest newPurchaseRequest, ConstraintValidatorContext context) {
        return newPurchaseRequest.getCpf() != null || newPurchaseRequest.getCnpj() != null;
    }
}
