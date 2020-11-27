package com.zup.casadocodigo.Category;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NewCategoryRequestTest {
    @Test
    @DisplayName("Cria um novo model Category, ao chamar toModel com dados válidos")
    void test1() {
        NewCategoryRequest newCategoryRequest = new NewCategoryRequest("Categoria1");

        Assertions.assertNotNull(newCategoryRequest.toModel());
    }

    @Test
    @DisplayName("Lança uma exception, ao chamar toModel com dados inválidos")
    void test2() {
        NewCategoryRequest newCategoryRequest = new NewCategoryRequest(null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> newCategoryRequest.toModel());
    }

}
