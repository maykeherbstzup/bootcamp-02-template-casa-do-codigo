package com.zup.casadocodigo.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @PersistenceContext
    EntityManager em;

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody @Valid NewBookRequest newBookRequest) {
        Book book = newBookRequest.toModel(em);

        this.bookRepository.save(book);

        return ResponseEntity.ok().build();
    }
}
