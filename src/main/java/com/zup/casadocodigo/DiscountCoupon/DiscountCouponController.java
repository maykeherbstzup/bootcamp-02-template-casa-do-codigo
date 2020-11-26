package com.zup.casadocodigo.DiscountCoupon;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/discount-coupon")
public class DiscountCouponController {
    @PersistenceContext
    EntityManager em;

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody @Valid NewDiscountCouponRequest newDiscountCouponRequest) {
        DiscountCoupon discountCoupon = newDiscountCouponRequest.toModel();

        em.persist(discountCoupon);

        return ResponseEntity.ok().build();
    }

}
