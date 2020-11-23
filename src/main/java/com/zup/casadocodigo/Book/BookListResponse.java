package com.zup.casadocodigo.Book;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class BookListResponse {
    private UUID id;
    private String title;

    public BookListResponse(@NotNull UUID id, @NotBlank String title) {
        this.id = id;
        this.title = title;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
