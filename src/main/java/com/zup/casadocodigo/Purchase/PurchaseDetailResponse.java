package com.zup.casadocodigo.Purchase;

import com.zup.casadocodigo.Book.Book;
import com.zup.casadocodigo.DiscountCoupon.DiscountCoupon;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PurchaseDetailResponse {
    private String email;
    private String name;
    private String lastName;
    private String document;
    private String address;
    private String complement;
    private String city;
    private String country;
    private String state;
    private String phone;
    private String cep;
    private BigDecimal total;
    private Boolean hasCoupon = false;
    private BigDecimal totalWithDiscount = null;
    private List<PurchaseItemDetailResponse> items = new ArrayList<>();

    public PurchaseDetailResponse(Purchase purchase) {
        this.email = purchase.getEmail();
        this.name = purchase.getName();
        this.lastName = purchase.getLastName();
        this.document = purchase.getDocument();
        this.address = purchase.getComplement();
        this.complement = purchase.getComplement();
        this.city = purchase.getCity();
        this.country = purchase.getCountry().getName();
        this.phone = purchase.getPhone();
        this.cep = purchase.getCep();
        this.total = purchase.getTotalCalculated();

        if (purchase.getState() != null) {
            this.state = purchase.getState().getName();
        }

        purchase.getItems().stream().forEach(item -> {
            Book book = item.getBook();
            PurchaseItemDetailResponse responseItem = new PurchaseItemDetailResponse(
                    book.getTitle(),
                    book.getPrice(),
                    item.getQuantity()
            );

            this.items.add(responseItem);
        });

        DiscountCoupon discountCoupon = purchase.getDiscountCoupon();

        if (discountCoupon != null) {
            this.hasCoupon = true;
            this.totalWithDiscount = purchase.getTotal();
        }
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDocument() {
        return document;
    }

    public String getAddress() {
        return address;
    }

    public String getComplement() {
        return complement;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getPhone() {
        return phone;
    }

    public String getCep() {
        return cep;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Boolean getHasCoupon() {
        return hasCoupon;
    }

    public BigDecimal getTotalWithDiscount() {
        return totalWithDiscount;
    }

    public List<PurchaseItemDetailResponse> getItems() {
        return items;
    }
}
