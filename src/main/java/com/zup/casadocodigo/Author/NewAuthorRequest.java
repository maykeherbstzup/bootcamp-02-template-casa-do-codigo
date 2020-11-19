package com.zup.casadocodigo.Author;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NewAuthorRequest {
    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(max=400)
    private String description;

    public NewAuthorRequest(@NotBlank String name, @NotBlank @Email String email, @NotBlank @Size(max=400) String description) {
        super();

        this.name = name;
        this.email = email;
        this.description = description;
    }

    public Author toModel() {
        Author author = new Author(this.name, this.email, this.description);

        return author;
    }
}