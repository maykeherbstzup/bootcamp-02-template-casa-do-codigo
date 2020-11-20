package com.zup.casadocodigo.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody @Valid NewCategoryRequest newCategoryRequest) {
        Category category = newCategoryRequest.toModel();

        this.categoryRepository.save(category);

        return ResponseEntity.ok().build();
    }
}
