package com.zup.casadocodigo.Purchase;

import java.math.BigDecimal;

public class PurchaseItemDetailResponse {
    private String title;
    private BigDecimal price;
    private int quantity;

    public PurchaseItemDetailResponse(String title, BigDecimal price, int quantity) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

}
