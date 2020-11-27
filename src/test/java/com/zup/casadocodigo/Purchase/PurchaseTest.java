package com.zup.casadocodigo.Purchase;

import com.zup.casadocodigo.Book.Book;
import com.zup.casadocodigo.Location.Country;
import com.zup.casadocodigo.Location.State;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Arrays;

public class PurchaseTest {
    @Test
    @DisplayName("Deve criar um Purchase quando informado dados válidos")
    void test1() {
        Country country = Mockito.mock(Country.class);
        State state = Mockito.mock(State.class);
        Book book = Mockito.mock(Book.class);

        PurchaseItem item = new PurchaseItem(book, 1);

        Purchase purchase = new Purchase.Builder()
                .setEmail("purchase@purchase.com")
                .setName("luizinho")
                .setLastName("silva")
                .setDocument("47.960.950/0001-21")
                .setAddress("rua xxx")
                .setComplement("casa")
                .setCity("São Paulo")
                .setCountry(country)
                .setState(state)
                .setPhone("0115656565656")
                .setCep("89232466")
                .setTotal(BigDecimal.valueOf(100))
                .setItems(Arrays.asList(item))
                .setStatus(PurchaseStatus.INITIALIZED)
                .build();

        Assertions.assertNotNull(purchase);
    }
}