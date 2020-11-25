package com.zup.casadocodigo.Location;

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
@RequestMapping("/country")
public class CountryController {
    @PersistenceContext
    EntityManager em;

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody @Valid NewCountryRequest newCountryRequest,
                                    UriComponentsBuilder UriBuilder) {
        Country country = newCountryRequest.toModel();

        em.persist(country);

        URI locationURI = UriBuilder.path("country/{id}").buildAndExpand(country.getId().toString()).toUri();

        return ResponseEntity.created(locationURI).build();
    }
}
