package com.zup.casadocodigo.Category;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

public class CategoryTest {
    @Test
    @DisplayName("Deve criar um Category quando informado dados válidos")
    void test1() {
        Category category = new Category("Category1");

        Assertions.assertNotNull(category);
    }

    @Test
    @DisplayName("Deve lançar uma exception quando algum dos parametros for invalido")
    void test2() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Category(null));
    }
}
