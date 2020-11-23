package com.zup.casadocodigo.Book;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zup.casadocodigo.Category.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {
    @PersistenceContext
    EntityManager em;

    @PostMapping
    @Transactional
    public ResponseEntity<?> createBook(@RequestBody @Valid NewBookRequest newBookRequest) {
        Book book = newBookRequest.toModel(em);

        em.persist(book);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getBooks() {
        Query query = em.createQuery("SELECT NEW " + BookResponse.class.getName() + "(id, title) FROM Book b",
                BookResponse.class);

        List<BookResponse> result = query.getResultList();

        return ResponseEntity.ok().body(result);
    }
}
