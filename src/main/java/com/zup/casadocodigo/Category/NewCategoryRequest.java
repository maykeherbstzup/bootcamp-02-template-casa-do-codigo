package com.zup.casadocodigo.Category;

import com.zup.casadocodigo.shared.validation.Unique;

import javax.validation.constraints.NotBlank;

public class NewCategoryRequest {
    @NotBlank
    @Unique(className = "Category", fieldName = "name", message = "{category.unique}")
    private String name;

    public NewCategoryRequest() {}

    public NewCategoryRequest(@NotBlank String name) {
        this.name = name;
    }

    public Category toModel() {
        Category category = new Category(this.name);

        return category;
    }
}
