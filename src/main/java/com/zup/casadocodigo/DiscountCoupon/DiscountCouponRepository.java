package com.zup.casadocodigo.DiscountCoupon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DiscountCouponRepository extends JpaRepository<DiscountCoupon, UUID> {
    public DiscountCoupon findByCode(String code);
}
