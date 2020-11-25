package com.zup.casadocodigo.Location;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(
    name = "state",
    uniqueConstraints = {@UniqueConstraint(name = "unique_state", columnNames = { "name" })}
)
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, updatable = false, nullable = false)
    private UUID id;

    @NotBlank
    private String name;

    @NotNull
    @ManyToOne
    private Country country;

    @Deprecated
    private State() {};

    public State(@NotBlank String name, @NotBlank Country country) {
        Assert.hasText(name, "Campo 'nome' não pode estar em branco");
        Assert.notNull(country, "Campo 'país' não pode estar em branco");

        this.name = name;
        this.country = country;
    };

    public UUID getId() {
        return id;
    }

    public boolean belongsToCountry(Country country) {
        return this.country.getId().equals(country.getId());
    }
}
