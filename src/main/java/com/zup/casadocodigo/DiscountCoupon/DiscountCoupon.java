package com.zup.casadocodigo.DiscountCoupon;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(
        name = "discount_coupon",
        uniqueConstraints = {@UniqueConstraint(name = "unique_code", columnNames = {"code"})}
)
public class DiscountCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, updatable = false, nullable = false)
    private UUID id;

    @NotBlank
    private String code;

    @NotNull
    @Positive
    private BigDecimal percentage;

    @NotNull
    @Future
    private LocalDate expirationDate;

    @Deprecated
    private DiscountCoupon() {
    }

    public DiscountCoupon(@NotBlank String code, @NotNull @Positive BigDecimal percentage,
                          @NotNull @FutureOrPresent LocalDate expirationDate) {
        this.code = code;
        this.percentage = percentage;
        this.expirationDate = expirationDate;
    }
}
