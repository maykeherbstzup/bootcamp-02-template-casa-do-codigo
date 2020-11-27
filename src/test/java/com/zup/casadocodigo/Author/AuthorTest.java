package com.zup.casadocodigo.Author;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AuthorTest {
    @Test
    @DisplayName("Deve criar um Author quando informado dados válidos")
    void test1() {
        Author author = new Author("Author1", "author@author.com", "xxx");

        Assertions.assertNotNull(author);
    }

    @ParameterizedTest
    @DisplayName("Deve lançar uma exception quando algum dos parametros for invalido")
    @CsvSource({
            ", author@author.com, description xxx",
            "authorName, , description xxx",
            "authorName, author@author.com,",
    })
    void test2(String name, String email, String description) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Author(name, email, description));
    }
}
