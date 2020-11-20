package com.zup.casadocodigo.Book;

import com.zup.casadocodigo.Author.Author;
import com.zup.casadocodigo.Category.Category;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(
    name = "book",
    uniqueConstraints = {
        @UniqueConstraint(name = "unique_title", columnNames = {"title"}),
        @UniqueConstraint(name = "unique_isbn", columnNames = {"isbn"})
    }
)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, updatable = false, nullable = false)
    private UUID id;

    @NotBlank
    private String title;

    @NotBlank
    @Size(min = 1, max = 500)
    private String summary;

    @NotBlank
    @Min(20)
    private BigDecimal price;

    @NotBlank
    @Min(100)
    private int numberOfPages;

    @NotBlank
    private String isbn;

    @NotBlank
    @Future
    private LocalDate publicationDate;

    @NotNull
    @OneToOne
    private Category category;

    @NotNull
    @ManyToMany
    private Author author;

    public Book(@NotBlank String title, @NotBlank @Size(min = 1, max = 500) String summary,
                @NotBlank @Min(20) BigDecimal price, @NotBlank @Min(100) int numberOfPages, @NotBlank String isbn,
                @NotBlank @Future LocalDate publicationDate, @NotNull Category category, @NotNull Author author) {
        this.title = title;
        this.summary = summary;
        this.price = price;
        this.numberOfPages = numberOfPages;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
        this.category = category;
        this.author = author;
    }
}