package com.zup.casadocodigo.Author;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    @Size(max=400)
    private String description;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Author(@NotBlank String name, @NotBlank @Email String email, @NotBlank @Size(max=400) String description) {
        Assert.hasText(name, "Campo 'nome' não pode estar em branco");
        Assert.hasText(email, "Campo 'email' não pode estar em branco");
        Assert.hasText(description, "Campo 'descrição' não pode estar em branco");
        Assert.isTrue(description.length() <= 400, "Campo 'descrição' deve ter no máximo 400 caracteres");

        this.name = name;
        this.email = email;
        this.description = description;
    }
}
