package com.zup.casadocodigo.Book;

import java.math.BigDecimal;
import java.util.UUID;

public class BookDetailsResponse {
    private String title;
    private String contentAbstract;
    private String summary;
    private BigDecimal price;
    private int numberOfPages;
    private String isbn;
    private String categoryName;
    private String authorName;

    public BookDetailsResponse(Book book) {
        this.title = book.getTitle();
        this.contentAbstract = book.getContentAbstract();
        this.summary = book.getSummary();
        this.price = book.getPrice();
        this.numberOfPages = book.getNumberOfPages();
        this.isbn = book.getIsbn();
        this.categoryName = book.getCategory().getName();
        this.authorName = book.getAuthor().getName();
    }

    public String getTitle() {
        return title;
    }

    public String getContentAbstract() {
        return contentAbstract;
    }

    public String getSummary() {
        return summary;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getAuthorName() {
        return authorName;
    }
}
