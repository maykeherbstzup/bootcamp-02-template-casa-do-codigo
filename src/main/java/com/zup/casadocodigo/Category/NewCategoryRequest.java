package com.zup.casadocodigo.Category;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.zup.casadocodigo.shared.validation.Unique;

import javax.validation.constraints.NotBlank;

public class NewCategoryRequest {
    @NotBlank
    @Unique(entityClass = Category.class, fieldName = "name", message = "{category.name.unique}")
    private String name;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NewCategoryRequest(@NotBlank String name) {
        this.name = name;
    }

    public Category toModel() {
        Category category = new Category(this.name);

        return category;
    }
}
