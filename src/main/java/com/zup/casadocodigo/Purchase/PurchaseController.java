package com.zup.casadocodigo.Purchase;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @PersistenceContext
    EntityManager em;

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody @Valid NewPurchaseRequest newPurchaseRequest, UriComponentsBuilder UriBuilder) {
        Purchase purchase = newPurchaseRequest.toModel(em);

        em.persist(purchase);

        URI locationURI = UriBuilder.path("purchase/{id}").buildAndExpand(purchase.getId().toString()).toUri();

        return ResponseEntity.created(locationURI).build();
    }
}
