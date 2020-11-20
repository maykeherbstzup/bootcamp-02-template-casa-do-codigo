package com.zup.casadocodigo.Book;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zup.casadocodigo.Author.Author;
import com.zup.casadocodigo.Category.Category;
import com.zup.casadocodigo.shared.validation.IdExists;
import com.zup.casadocodigo.shared.validation.Unique;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/book")
public class NewBookRequest {
    @NotBlank
    @Unique(entityClass = Book.class, fieldName = "title", message = "{book.title.unique}")
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
    @Unique(entityClass = Book.class, fieldName = "isbn", message = "{book.isbn.unique}")
    private String isbn;

    @NotBlank
    @Future
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate publicationDate;

    @NotNull
    @IdExists(entityClass = Category.class, fieldName = "id", message = "{book.category.notExists}")
    private String categoryId;

    @NotNull
    @IdExists(entityClass = Author.class, fieldName = "id", message = "{book.author.notExists}")
    private String authorId;

    public NewBookRequest(@NotBlank String title, @NotBlank @Size(min = 1, max = 500) String summary,
                          @NotBlank @Min(20) BigDecimal price, @NotBlank @Min(100) int numberOfPages,
                          @NotBlank String isbn, @NotBlank @Future LocalDate publicationDate,
                          @NotNull String categoryId, @NotNull String authorId) {
        this.title = title;
        this.summary = summary;
        this.price = price;
        this.numberOfPages = numberOfPages;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
        this.categoryId = categoryId;
        this.authorId = authorId;
    }

    public Book toModel(EntityManager em) {
        Author author = em.find(Author.class, this.authorId);
        Category category = em.find(Category.class, this.categoryId);

        Assert.state(author != null, "O autor informado não existe");
        Assert.state(category != null, "A categoria informada não existe");

        Book book = new Book(
            this.title,
            this.summary,
            this.price,
            this.numberOfPages,
            this.isbn,
            this.publicationDate,
            category,
            author
        );

        return book;
    }
}
