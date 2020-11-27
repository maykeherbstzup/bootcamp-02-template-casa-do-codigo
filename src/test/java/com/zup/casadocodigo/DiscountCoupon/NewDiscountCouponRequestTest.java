package com.zup.casadocodigo.DiscountCoupon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class NewDiscountCouponRequestTest {
    @Test
    @DisplayName("Cria um novo model DiscountCoupon, ao chamar toModel com dados válidos")
    void test1() {
        NewDiscountCouponRequest newDiscountCouponRequest = new NewDiscountCouponRequest("DESC10",
                BigDecimal.valueOf(25.5));

        newDiscountCouponRequest.setExpirationDate(LocalDate.of(2100, 01, 01));

        Assertions.assertNotNull(newDiscountCouponRequest.toModel());
    }

    @Test
    @DisplayName("Lança uma exception, ao chamar toModel com dados inválidos")
    void test2() {
        NewDiscountCouponRequest newDiscountCouponRequest = new NewDiscountCouponRequest(null, null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> newDiscountCouponRequest.toModel());
    }
}
