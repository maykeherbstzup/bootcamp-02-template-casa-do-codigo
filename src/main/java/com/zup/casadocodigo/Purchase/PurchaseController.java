package com.zup.casadocodigo.Purchase;

import com.zup.casadocodigo.DiscountCoupon.DiscountCouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @PersistenceContext
    EntityManager em;

    @Autowired
    DiscountCouponRepository discountCouponRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody @Valid NewPurchaseRequest newPurchaseRequest, UriComponentsBuilder UriBuilder) {
        Purchase purchase = newPurchaseRequest.toModel(em, discountCouponRepository);

        em.persist(purchase);

        URI locationURI = UriBuilder.path("purchase/{id}").buildAndExpand(purchase.getId().toString()).toUri();

        return ResponseEntity.created(locationURI).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetail(@PathVariable(value = "id") String id) {
        Purchase purchase = em.find(Purchase.class, UUID.fromString(id));

        PurchaseDetailResponse purchaseDetailResponse = new PurchaseDetailResponse(purchase);

        return ResponseEntity.ok(purchaseDetailResponse);
    }
}
