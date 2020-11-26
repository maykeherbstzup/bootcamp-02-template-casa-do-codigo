package com.zup.casadocodigo.Purchase;

import com.zup.casadocodigo.Book.Book;
import com.zup.casadocodigo.shared.validation.IdExists;

import javax.persistence.EntityManager;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class NewPurchaseItemsRequest {
    @NotBlank
    @IdExists(entityClass = Book.class, fieldName = "id", message = "{purchase.cart.book.notExists}")
    private String bookId;

    @NotNull
    @Min(value = 1)
    private int quantity;

    public NewPurchaseItemsRequest(@NotBlank String bookId, @NotNull @Min(1) int quantity) {
        this.bookId = bookId;
        this.quantity = quantity;
    }

    public PurchaseItem toModel(EntityManager em) {
        Book book = em.find(Book.class, UUID.fromString(this.bookId));

        PurchaseItem item = new PurchaseItem(book, this.quantity);

        return item;
    }

    public String getBookId() {
        return bookId;
    }
}
