package com.zup.casadocodigo.Book;

import com.zup.casadocodigo.Author.Author;
import com.zup.casadocodigo.Category.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

public class BookTest {
    Category category = Mockito.mock(Category.class);
    Author author = Mockito.mock(Author.class);

    @Test
    @DisplayName("Deve criar um Book quando informado dados válidos")
    void test1() {
        Book book = new Book.Builder()
                .setTitle("titulo")
                .setContentAbstract("resumo")
                .setSummary("sumário")
                .setPrice(BigDecimal.valueOf(20))
                .setNumberOfPages(100)
                .setIsbn("123-123-123")
                .setPublicationDate(LocalDate.now())
                .setCategory(this.category)
                .setAuthor(this.author)
                .build();

        Assertions.assertNotNull(book);
    }

    @ParameterizedTest
    @DisplayName("Deve lançar uma exception quando algum dos parametros obrigatórios for nulo")
    @MethodSource("getArgumentsForTest2")
    void test2(String title, String contentAbstract, String summary, BigDecimal price, Integer numberOfPages,
               String isbn, LocalDate publicationDate, Category category, Author author) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Book.Builder()
                .setTitle(title)
                .setContentAbstract(contentAbstract)
                .setSummary(summary)
                .setPrice(price)
                .setNumberOfPages(numberOfPages)
                .setIsbn(isbn)
                .setPublicationDate(publicationDate)
                .setCategory(category)
                .setAuthor(author)
                .build();
        });
    }

    private static Stream<Arguments> getArgumentsForTest2() {
        Category category = Mockito.mock(Category.class);
        Author author = Mockito.mock(Author.class);

        return Stream.of(
          Arguments.of(null, "resumo", "sumario", BigDecimal.valueOf(20), 100, "123-123", LocalDate.now(), category,
                  author),
          Arguments.of("title", null, "sumario", BigDecimal.valueOf(20), 100, "123-123", LocalDate.now(), category,
                  author),
          Arguments.of("title", "resumo", null, BigDecimal.valueOf(20), 100, "123-123", LocalDate.now(), category,
                  author),
          Arguments.of("title", "resumo", "sumario", null, 100, "123-123", LocalDate.now(), category, author),
          Arguments.of("title", "resumo", "sumario", BigDecimal.valueOf(20), 100, null, LocalDate.now(), category,
                  author),
          Arguments.of("title", "resumo", "sumario", BigDecimal.valueOf(20), 100, "123-123", null, category, author),
          Arguments.of("title", "resumo", "sumario", BigDecimal.valueOf(20), 100, "123-123", LocalDate.now(), null,
                  author),
          Arguments.of("title", "resumo", "sumario", BigDecimal.valueOf(20), 100, "123-123", LocalDate.now(), category,
                  null)
        );
    }
}
