package com.zup.casadocodigo.DiscountCoupon;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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
                          @NotNull @Future LocalDate expirationDate) {
        Assert.hasText(code, "Código não deve estar em branco");
        Assert.isTrue(percentage != null && percentage.compareTo(BigDecimal.ZERO) == 1, "Percentual deve ser maior " +
                "que zero");
        Assert.isTrue(expirationDate != null && LocalDate.now().isBefore(expirationDate),
                "Data de expiração deve estar no futuro");

        this.code = code;
        this.percentage = percentage;
        this.expirationDate = expirationDate;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(this.expirationDate);
    }
}
