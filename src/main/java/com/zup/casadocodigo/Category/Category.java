package com.zup.casadocodigo.Category;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Table(
    name="category",
    uniqueConstraints = {@UniqueConstraint(name="unique_category", columnNames = {"name"})}
)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, updatable = false, nullable = false)
    private UUID id;

    @NotBlank
    private String name;

    @Deprecated
    private Category() {}

    public Category(@NotBlank String name) {
        Assert.hasText(name, "Campo 'nome' não pode estar em branco");

        this.name = name;
    }

    public String getName() {
        return name;
    }
}