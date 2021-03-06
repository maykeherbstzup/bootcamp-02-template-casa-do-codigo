package com.zup.casadocodigo.Location;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@Entity
@Table(
    name = "country",
    uniqueConstraints = {@UniqueConstraint(name = "unique_country", columnNames = { "name" })}
)
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, updatable = false, nullable = false)
    private UUID id;

    @NotBlank
    private String name;

    @OneToMany(mappedBy = "country")
    private List<State> states;

    @Deprecated
    private Country() {};

    public Country(@NotBlank String name) {
        Assert.hasText(name, "Campo 'nome' não pode estar em branco");

        this.name = name;
    };

    public UUID getId() {
        return id;
    }

    public List<State> getStates() {
        return states;
    }

    public String getName() {
        return name;
    }
}
