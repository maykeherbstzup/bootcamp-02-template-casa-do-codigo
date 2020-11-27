package com.zup.casadocodigo.DiscountCoupon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

public class DiscountCouponTest {
    @Test
    @DisplayName("Deve criar um DiscountCoupon quando informado dados válidos")
    void test1() {
        DiscountCoupon discountCoupon = new DiscountCoupon("DESCONTO10", BigDecimal.valueOf(25), LocalDate.of(2100,01,01));

        Assertions.assertNotNull(discountCoupon);
    }

    @ParameterizedTest
    @DisplayName("Deve lançar uma exception quando algum dos parametros for invalido")
    @MethodSource("getArgumentsForTest2")
    void test2(String code, BigDecimal percentage, LocalDate expirationDate) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new DiscountCoupon(code, percentage,
                expirationDate));
    }

    private static Stream<Arguments> getArgumentsForTest2() {
        return Stream.of(
          Arguments.of(null, BigDecimal.valueOf(25), LocalDate.of(2100, 01, 01)),
          Arguments.of("DESCXX", null, LocalDate.of(2100, 01, 01)),
          Arguments.of("DESCXX", BigDecimal.valueOf(25), null)
        );
    }
}
