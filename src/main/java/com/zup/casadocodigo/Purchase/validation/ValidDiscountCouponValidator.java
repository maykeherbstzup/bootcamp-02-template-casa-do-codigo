package com.zup.casadocodigo.Purchase.validation;

import com.zup.casadocodigo.DiscountCoupon.DiscountCoupon;
import com.zup.casadocodigo.DiscountCoupon.DiscountCouponRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidDiscountCouponValidator implements ConstraintValidator<ValidDiscountCoupon, String> {
    @Autowired
    DiscountCouponRepository discountCouponRepository;

    @Override
    public boolean isValid(String discountCouponCode, ConstraintValidatorContext context) {
        if (discountCouponCode == null) {
            return true;
        }

        DiscountCoupon discountCoupon = this.discountCouponRepository.findByCode(discountCouponCode);

        if (discountCoupon == null || discountCoupon.isExpired()) {
            return false;
        }

        return true;
    }
}
