package com.zup.casadocodigo.Location;

import com.zup.casadocodigo.shared.validation.IdExists;
import com.zup.casadocodigo.shared.validation.Unique;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class NewStateRequest {
    @NotBlank
    @Unique(entityClass = State.class, fieldName = "name", message = "{location.state.name.unique}")
    private String name;

    @NotBlank
    @IdExists(entityClass = Country.class, fieldName = "id", message = "{location.country.notExists}")
    private String countryId;

    public NewStateRequest(@NotBlank String name, @NotBlank String countryId) {
        this.name = name;
        this.countryId = countryId;
    }

    public State toModel(EntityManager em) {
        Country country = em.find(Country.class, UUID.fromString(this.countryId));

        State state = new State(this.name, country);

        return state;
    }
}
