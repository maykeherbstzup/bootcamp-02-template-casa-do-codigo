package com.zup.casadocodigo.Book;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.*;

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
    public ResponseEntity<List<BookListResponse>> getBooks() {
        Query query = em.createQuery("SELECT NEW " + BookListResponse.class.getName() + "(id, title) FROM Book b",
                BookListResponse.class);

        List<BookListResponse> result = query.getResultList();

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDetailsResponse> getBook(@PathVariable(value = "id") UUID id) {
        Book book = em.find(Book.class, id);

        if (book == null) {
            return ResponseEntity.notFound().build();
        }

        BookDetailsResponse bookDetailsResponse = new BookDetailsResponse(book);

        return ResponseEntity.ok().body(bookDetailsResponse);
    }
}
