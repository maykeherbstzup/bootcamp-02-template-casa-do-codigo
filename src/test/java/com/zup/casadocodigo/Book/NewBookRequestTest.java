package com.zup.casadocodigo.Book;

import com.zup.casadocodigo.Author.Author;
import com.zup.casadocodigo.Category.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class NewBookRequestTest {
    @Test
    @DisplayName("Cria um novo model Book, ao chamar toModel com dados válidos")
    void test1() {
        Category category = Mockito.mock(Category.class);
        Author author = Mockito.mock(Author.class);

        UUID categoryId = UUID.randomUUID();
        UUID authorId = UUID.randomUUID();

        EntityManager em = Mockito.mock(EntityManager.class);
        Mockito.doReturn(category).when(em).find(Category.class, categoryId);
        Mockito.doReturn(author).when(em).find(Author.class, authorId);

        NewBookRequest newBookRequest = new NewBookRequest(
                "Título",
                "resumo",
                "sumário",
                BigDecimal.valueOf(20),
                100,
                "123-123-123",
                categoryId.toString(),
                authorId.toString()
        );

        newBookRequest.setPublicationDate(LocalDate.now());

        Assertions.assertNotNull(newBookRequest.toModel(em));
    }
}
