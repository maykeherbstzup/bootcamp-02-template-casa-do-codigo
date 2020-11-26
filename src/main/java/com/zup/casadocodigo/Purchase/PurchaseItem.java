package com.zup.casadocodigo.Purchase;

import com.zup.casadocodigo.Book.Book;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name= "purchase_item")
public class PurchaseItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, updatable = false, nullable = false)
    private UUID id;

    @NotNull
    private int quantity;

    @NotNull
    @ManyToOne
    private Book book;

    @ManyToOne
    private Purchase purchase;

    public PurchaseItem(@NotNull Book book, @NotNull int quantity) {
        this.quantity = quantity;
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public int getQuantity() {
        return quantity;
    }
}
