package com.zup.casadocodigo.Book;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class BookResponse {
    @NotBlank
    private UUID id;

    @NotBlank
    private String title;

    public BookResponse(@NotNull UUID id, @NotBlank String title) {
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
