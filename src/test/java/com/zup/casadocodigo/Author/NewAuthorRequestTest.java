package com.zup.casadocodigo.Author;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class NewAuthorRequestTest {
    @Test
    @DisplayName("Cria um novo model Author, ao chamar toModel com dados válidos")
    void test1() {
        NewAuthorRequest newAuthorRequest = new NewAuthorRequest("Autor1", "autor@autor.com", "xxx");

        Assertions.assertNotNull(newAuthorRequest.toModel());
    }

    @Test
    @DisplayName("Lança uma exception, ao chamar toModel com dados inválidos")
    void test2() {
        Random random = new Random();
        String description = random.ints(97, 123)
            .limit(401)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();

        NewAuthorRequest newAuthorRequest = new NewAuthorRequest("Autor1", "autor@autor.com", description);

        Assertions.assertThrows(IllegalArgumentException.class, () -> newAuthorRequest.toModel());
    }
}
