package com.zup.casadocodigo.Purchase;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;

public class NewPurchaseCartRequest {
    @NotNull
    @DecimalMin(value = "0", inclusive = false)
    private BigDecimal total;

    @NotNull
    @Size(min = 1, message = "{purchase.cart.quantity.min}")
    @Valid
    private Set<NewPurchaseItemsRequest> items;

    public NewPurchaseCartRequest(@NotNull @DecimalMin(value = "0", inclusive = false) BigDecimal total,
                                  @NotNull @Size(min = 1) Set<NewPurchaseItemsRequest> items) {
        this.total = total;
        this.items = items;
    }

    public Set<NewPurchaseItemsRequest> getItems() {
        return items;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
