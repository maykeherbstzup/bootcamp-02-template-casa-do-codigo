package com.zup.casadocodigo.Book;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class BookListResponseTest {
    @Test
    @DisplayName("Cria um novo BookListResponse com dados válidos")
    void test1() {
        Assertions.assertNotNull(new BookListResponse(UUID.randomUUID(), "Título"));
    }
}
