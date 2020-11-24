package com.zup.casadocodigo.Book;

import com.zup.casadocodigo.Author.Author;
import com.zup.casadocodigo.Category.Category;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
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

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, updatable = false, nullable = false)
    private UUID id;

    @NotBlank
    private String title;

    @NotBlank
    @Size(min = 1, max = 500)
    private String contentAbstract;

    @NotBlank
    private String summary;

    @NotNull
    @Min(20)
    private BigDecimal price;

    @NotNull
    @Min(100)
    private int numberOfPages;

    @NotBlank
    private String isbn;

    @NotNull
    @Future
    private LocalDate publicationDate;

    @NotNull
    @ManyToOne
    private Category category;

    @NotNull
    @ManyToOne
    private Author author;

    @Deprecated
    private Book() {}

    private Book(@NotBlank String title, @NotBlank @Size(min = 1, max = 500) String contentAbstract,
                 @NotBlank String summary, @NotNull @Min(20) BigDecimal price, @NotBlank @Min(100) int numberOfPages,
                 @NotBlank String isbn, @NotBlank @Future LocalDate publicationDate, @NotNull Category category,
                 @NotNull Author author) {

        Assert.hasText(title, "Campo 'Titulo' não pode estar em branco");
        Assert.hasText(contentAbstract, "Campo 'Resumo' não pode estar em branco");
        Assert.isTrue(contentAbstract.length() <= 500, "Campo 'descrição' deve ter no máximo 500 caracteres");
        Assert.hasText(summary, "Campo 'Sumário' não pode estar em branco");
        Assert.notNull(price, "Campo 'Preço' não pode estar em branco");
        Assert.isTrue(price.compareTo(new BigDecimal(20)) >= 0, "Campo 'preço' não pode ser menor que 20");
        Assert.isTrue(numberOfPages >= 100, "Campo 'Número de páginas' não pode ser menor que 100");
        Assert.hasText(isbn, "Campo 'ISBN' não pode estar em branco");
        Assert.notNull(publicationDate, "Campo 'Data de publicação' não pode estar em branco");
        Assert.notNull(category, "Campo 'Categoria' não pode estar em branco");
        Assert.notNull(author, "Campo 'Autor' não pode estar em branco");


        this.title = title;
        this.contentAbstract = contentAbstract;
        this.summary = summary;
        this.price = price;
        this.numberOfPages = numberOfPages;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
        this.category = category;
        this.author = author;
    }

    public UUID getId() {
        return id;
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

    public Category getCategory() {
        return category;
    }

    public Author getAuthor() {
        return author;
    }

    public static class Builder {
        private String title;
        private String contentAbstract;
        private String summary;
        private BigDecimal price;
        private int numberOfPages;
        private String isbn;
        private LocalDate publicationDate;
        private Category category;
        private Author author;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setSummary(String summary) {
            this.summary = summary;
            return this;
        }

        public Builder setContentAbstract(String contentAbstract) {
            this.contentAbstract = contentAbstract;
            return this;
        }

        public Builder setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder setNumberOfPages(int numberOfPages) {
            this.numberOfPages = numberOfPages;
            return this;
        }

        public Builder setIsbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Builder setPublicationDate(LocalDate publicationDate) {
            this.publicationDate = publicationDate;
            return this;
        }

        public Builder setCategory(Category category) {
            this.category = category;
            return this;
        }

        public Builder setAuthor(Author author) {
            this.author = author;
            return this;
        }

        public Book build() {
            Book book = new Book(
                this.title,
                this.contentAbstract,
                this.summary,
                this.price,
                this.numberOfPages,
                this.isbn,
                this.publicationDate,
                this.category,
                this.author
            );

            return book;
        }
    }
}