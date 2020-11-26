package com.zup.casadocodigo.Purchase;

import com.zup.casadocodigo.Location.Country;
import com.zup.casadocodigo.Location.State;
import com.zup.casadocodigo.Purchase.validation.PurchaseRequest;
import com.zup.casadocodigo.shared.validation.IdExists;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@PurchaseRequest
public class NewPurchaseRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    @NotBlank
    private String document;

    @NotBlank
    private String address;

    @NotBlank
    private String complement;

    @NotBlank
    private String city;

    @NotBlank

    // TODO criar validator pra UUID
    @IdExists(entityClass = Country.class, fieldName = "id", message = "{location.country.notExists}")
    private String countryId;

    @IdExists(entityClass = State.class, fieldName = "id", message = "{location.state.notExists}")
    private String stateId;

    @NotBlank
    private String phone;

    @NotBlank
    private String cep;

    @NotNull
    @Valid
    private NewPurchaseCartRequest cart;

    public NewPurchaseRequest(@NotBlank @Email String email, @NotBlank String name, @NotBlank String lastName,
                              @NotBlank String document, @NotBlank String address, @NotBlank String complement,
                              @NotBlank String city, @NotBlank String countryId, String stateId, @NotBlank String phone,
                              @NotBlank String cep, @NotNull NewPurchaseCartRequest cart) {
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.document = document;
        this.address = address;
        this.complement = complement;
        this.city = city;
        this.countryId = countryId;
        this.stateId = stateId;
        this.phone = phone;
        this.cep = cep;
        this.cart = cart;
    }

    public String getDocument() {
        return document;
    }

    public String getCountryId() {
        return countryId;
    }

    public String getStateId() {
        return stateId;
    }

    public NewPurchaseCartRequest getCart() {
        return cart;
    }

    public Purchase toModel(EntityManager em) {
        Country country = em.find(Country.class, UUID.fromString(this.countryId));
        State state = null;

        if (this.stateId != null && !this.stateId.isBlank()) {
            state = em.find(State.class, UUID.fromString(this.stateId));
        }

        List<PurchaseItem> itemsList = this.cart.getItems()
                .stream()
                .map(item -> item.toModel(em))
                .collect(Collectors.toList());

        Purchase purchase = new Purchase.Builder()
                .setEmail(this.email)
                .setName(this.name)
                .setLastName(this.lastName)
                .setDocument(this.document)
                .setAddress(this.address)
                .setComplement(this.complement)
                .setCity(this.city)
                .setCountry(country)
                .setState(state)
                .setPhone(this.phone)
                .setCep(this.cep)
                .setTotal(this.cart.getTotal())
                .setItems(itemsList)
                .setStatus(PurchaseStatus.INITIALIZED)
                .build();

        Assert.isTrue(purchase.getTotalCalculated().equals(this.cart.getTotal()),
                "O total informado não condiz com o total calculado");

        return purchase;
    }
}
