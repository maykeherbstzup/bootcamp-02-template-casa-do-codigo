package com.zup.casadocodigo.Category;

import com.zup.casadocodigo.shared.validation.Unique;

import javax.validation.constraints.NotBlank;

public class NewCategoryRequest {
    @NotBlank
    @Unique(entityClass = Category.class, fieldName = "name", message = "{category.name.unique}")
    private String name;

    public NewCategoryRequest() {}

    public NewCategoryRequest(@NotBlank String name) {
        this.name = name;
    }

    public Category toModel() {
        Category category = new Category(this.name);

        return category;
    }

    /**
     * Getter e setter necessários pois a princípio o jackson não consegue serializar o objeto com apenas uma
     * propriedade
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
