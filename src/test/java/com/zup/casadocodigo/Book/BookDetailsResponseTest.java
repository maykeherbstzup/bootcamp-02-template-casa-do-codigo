package com.zup.casadocodigo.Book;

import com.zup.casadocodigo.Author.Author;
import com.zup.casadocodigo.Category.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookDetailsResponseTest {
    @Test
    @DisplayName("Cria um novo BookDetailsResponse com dados válidos ao passar uma instancia de Book no construtor")
    void test1() {
        Category category = Mockito.mock(Category.class);
        Author author = Mockito.mock(Author.class);

        Mockito.doReturn("Categoria 1").when(category).getName();
        Mockito.doReturn("Autor 1").when(author).getName();

        Book book = new Book.Builder()
                .setTitle("título")
                .setContentAbstract("resumo")
                .setSummary("sumario")
                .setPrice(BigDecimal.valueOf(20))
                .setNumberOfPages(100)
                .setIsbn("123-123-23")
                .setPublicationDate(LocalDate.ofYearDay(2020,1))
                .setCategory(category)
                .setAuthor(author)
                .build();

        BookDetailsResponse bookDetailsResponse = new BookDetailsResponse(book);
        
        Assertions.assertEquals(bookDetailsResponse.getTitle(), "título");
        Assertions.assertEquals(bookDetailsResponse.getContentAbstract(), "resumo");
        Assertions.assertEquals(bookDetailsResponse.getSummary(), "sumario");
        Assertions.assertEquals(bookDetailsResponse.getPrice(), BigDecimal.valueOf(20));
        Assertions.assertEquals(bookDetailsResponse.getNumberOfPages(), 100);
        Assertions.assertEquals(bookDetailsResponse.getIsbn(), "123-123-23");
        Assertions.assertEquals(bookDetailsResponse.getCategoryName(), "Categoria 1");
        Assertions.assertEquals(bookDetailsResponse.getAuthorName(), "Autor 1");
    }
}
