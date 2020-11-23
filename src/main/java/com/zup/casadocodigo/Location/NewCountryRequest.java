package com.zup.casadocodigo.Location;

import com.zup.casadocodigo.shared.validation.Unique;

import javax.validation.constraints.NotBlank;

public class NewCountryRequest {
    @NotBlank
    @Unique(entityClass = Country.class, fieldName = "name", message = "{location.country.name.unique}")
    private String name;

    public NewCountryRequest() {}

    public NewCountryRequest(@NotBlank String name) {
        this.name = name;
    }

    /**
     * Getter e setter necessários pois a princípio o jackson não consegue serializar o objeto com apenas uma
     * propriedade
     */
    public String getName() {
        return name;
    }

    public Country toModel() {
        Country country = new Country(this.name);

        return country;
    }
}
