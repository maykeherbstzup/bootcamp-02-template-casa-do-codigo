package com.zup.casadocodigo.Book;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zup.casadocodigo.Author.Author;
import com.zup.casadocodigo.Category.Category;
import com.zup.casadocodigo.shared.validation.IdExists;
import com.zup.casadocodigo.shared.validation.Unique;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class NewBookRequest {
    @NotBlank
    @Unique(entityClass = Book.class, fieldName = "title", message = "{book.title.unique}")
    private String title;

    @NotBlank
    @Size(min = 1, max = 500)
    private String contentAbstract;

    @NotBlank
    private String summary;

    @Min(20)
    private BigDecimal price;

    @Min(100)
    private int numberOfPages;

    @NotBlank
    @Unique(entityClass = Book.class, fieldName = "isbn", message = "{book.isbn.unique}")
    private String isbn;

    @Future
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate publicationDate;

    @NotNull
    @IdExists(entityClass = Category.class, fieldName = "id", message = "{book.category.notExists}")
    private String categoryId;

    @NotNull
    @IdExists(entityClass = Author.class, fieldName = "id", message = "{book.author.notExists}")
    private String authorId;

    public NewBookRequest(@NotBlank String title, @NotBlank @Size(min = 1, max = 500) String contentAbstract,
                          @NotBlank String summary, @Min(20) BigDecimal price, @Min(100) int numberOfPages,
                          @NotBlank String isbn, @NotNull String categoryId, @NotNull String authorId) {
        this.title = title;
        this.contentAbstract = contentAbstract;
        this.summary = summary;
        this.price = price;
        this.numberOfPages = numberOfPages;
        this.isbn = isbn;
        this.categoryId = categoryId;
        this.authorId = authorId;
    }

    public Book toModel(EntityManager em) {
        Author author = em.find(Author.class, UUID.fromString(this.authorId));
        Category category = em.find(Category.class, UUID.fromString(this.categoryId));

        Book book = new Book.Builder()
                .setTitle(this.title)
                .setContentAbstract(this.contentAbstract)
                .setSummary(this.summary)
                .setPrice(this.price)
                .setNumberOfPages(this.numberOfPages)
                .setIsbn(this.isbn)
                .setPublicationDate(this.publicationDate)
                .setCategory(category)
                .setAuthor(author)
                .build();

        return book;
    }

    /**
     * Setter necessário pois o a princípio o jackson não consegue serializar a data passando pelo construtor
     */
    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }
}
