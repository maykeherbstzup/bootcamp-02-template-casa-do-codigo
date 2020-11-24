package com.zup.casadocodigo.Purchase;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @PersistenceContext
    EntityManager em;

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody @Valid NewPurchaseRequest newPurchaseRequest) {
        Purchase purchase = newPurchaseRequest.toModel(em);

        em.persist(purchase);

        return ResponseEntity.ok().build();
    }
}
