package com.zup.casadocodigo.Purchase;

import com.zup.casadocodigo.Location.Country;
import com.zup.casadocodigo.Location.State;
import com.zup.casadocodigo.shared.validation.PurchaseDocument;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@PurchaseDocument
public class NewPurchaseRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    @CPF
    private String cpf;

    @CNPJ
    private String cnpj;

    @NotBlank
    private String address;

    @NotBlank
    private String complement;

    @NotBlank
    private String city;

    @NotBlank
    private String countryId;

    // Obrigatório somente se o país tiver estado cadastrado
    private String stateId;

    @NotBlank
    private String phone;

    @NotBlank
    private String cep;

    public String getCnpj() {
        return cnpj;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj != null && cnpj.isBlank() ? null : cnpj;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf != null && cpf.isBlank() ? null : cpf;
    }

    public NewPurchaseRequest(@NotBlank @Email String email, @NotBlank String name, @NotBlank String lastName,
                              @NotBlank String address, @NotBlank String complement,
                              @NotBlank String city, @NotBlank String countryId, String stateId, @NotBlank String phone,
                              @NotBlank String cep) {
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.complement = complement;
        this.city = city;
        this.countryId = countryId;
        this.stateId = stateId;
        this.phone = phone;
        this.cep = cep;
    }

    public Purchase toModel(EntityManager em) {
        Country country = em.find(Country.class, UUID.fromString(this.countryId));
        State state = em.find(State.class, UUID.fromString(this.stateId));

        Purchase.Builder builder = new Purchase.Builder()
                .setEmail(this.email)
                .setName(this.name)
                .setLastName(this.lastName)
                .setAddress(this.address)
                .setComplement(this.complement)
                .setCity(this.city)
                .setCountry(country)
                .setState(state)
                .setPhone(this.phone)
                .setCep(this.cep);

        String document = this.cnpj != null ? this.cnpj : this.cpf;

        builder.setDocument(document);

        return builder.build();
    }
}
