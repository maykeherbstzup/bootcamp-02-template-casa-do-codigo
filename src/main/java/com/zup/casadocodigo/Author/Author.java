package com.zup.casadocodigo.Author;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
    name = "author",
    uniqueConstraints = { @UniqueConstraint(name = "unique_email", columnNames = { "email" }) }
)
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, updatable = false, nullable = false)
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(max=400)
    private String description;

    private LocalDateTime createdAt = LocalDateTime.now();

    @Deprecated
    private Author() {};

    public Author(@NotBlank String name, @NotBlank @Email String email, @NotBlank @Size(max=400) String description) {
        Assert.hasText(name, "Campo 'nome' não pode estar em branco");
        Assert.hasText(email, "Campo 'email' não pode estar em branco");
        Assert.hasText(description, "Campo 'descrição' não pode estar em branco");
        Assert.isTrue(description.length() <= 400, "Campo 'descrição' deve ter no máximo 400 caracteres");

        this.name = name;
        this.email = email;
        this.description = description;
    }

    public String getName() {
        return name;
    }
}
