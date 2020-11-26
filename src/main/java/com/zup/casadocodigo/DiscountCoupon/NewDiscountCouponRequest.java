package com.zup.casadocodigo.DiscountCoupon;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import com.zup.casadocodigo.shared.validation.Unique;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NewDiscountCouponRequest {
    @NotBlank
    @Unique(entityClass = DiscountCoupon.class, fieldName = "code", message = "{discountCoupon.code.unique}")
    private String code;

    @NotNull
    @Positive
    private BigDecimal percentage;

    @NotNull
    @Future
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate expirationDate;

    public NewDiscountCouponRequest(@NotBlank String code, @Positive BigDecimal percentage) {
        this.code = code;
        this.percentage = percentage;
    }

    /**
     * Setter necessário pois o a princípio o jackson não consegue serializar a data passando pelo construtor
     */
    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public DiscountCoupon toModel() {
        return new DiscountCoupon(this.code, this.percentage, this.expirationDate);
    }
}
