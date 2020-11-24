package com.zup.casadocodigo.Purchase;

import com.zup.casadocodigo.Location.Country;
import com.zup.casadocodigo.Location.State;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(
    name = "purchase"
)
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, updatable = false, nullable = false)
    private UUID id;

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

    @NotNull
    @ManyToOne
    private Country country;

    // Obrigatório somente se o país tiver estado cadastrado
    @ManyToOne
    private State state;

    @NotBlank
    private String phone;

    @NotBlank
    private String cep;

    @Deprecated
    private Purchase() {};

    private Purchase(@NotBlank @Email String email, @NotBlank String name, @NotBlank String lastName,
                    @NotBlank String document, @NotBlank String address, @NotBlank String complement,
                    @NotBlank String city, @NotBlank Country country, State state, @NotBlank String phone,
                    @NotBlank String cep) {
        Assert.hasText(email, "Campo 'email' não pode estar em branco");
        Assert.hasText(name, "Campo 'Nome' não pode estar em branco");
        Assert.hasText(lastName, "Campo 'Sobrenome' não pode estar em branco");
        Assert.hasText(document, "Campo 'Documento' não pode estar em branco");
        Assert.hasText(address, "Campo 'Documento' não pode estar em branco");
        Assert.hasText(complement, "Campo 'Complemento' não pode estar em branco");
        Assert.hasText(city, "Campo 'Cidade' não pode estar em branco");
        Assert.notNull(country, "Campo 'País' não pode estar em branco");
        Assert.hasText(phone, "Campo 'Telefone' não pode estar em branco");
        Assert.hasText(cep, "Campo 'Cep' não pode estar em branco");

        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.document = document;
        this.address = address;
        this.complement = complement;
        this.city = city;
        this.country = country;
        this.state = state;
        this.phone = phone;
        this.cep = cep;
    }

    public static class Builder {
        private String email;
        private String name;
        private String lastName;
        private String document;
        private String address;
        private String complement;
        private String city;
        private Country country;
        private State state;
        private String phone;
        private String cep;

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setDocument(String document) {
            this.document = document;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setComplement(String complement) {
            this.complement = complement;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setCountry(Country country) {
            this.country = country;
            return this;
        }

        public Builder setState(State state) {
            this.state = state;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setCep(String cep) {
            this.cep = cep;
            return this;
        }

        public Purchase build() {
            Purchase purchase = new Purchase(
                this.email,
                this.name,
                this.lastName,
                this.document,
                this.address,
                this.complement,
                this.city,
                this.country,
                this.state,
                this.phone,
                this.cep
            );

            return purchase;
        }
    }
}
