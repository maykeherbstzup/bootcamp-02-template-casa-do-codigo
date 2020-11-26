package com.zup.casadocodigo.Purchase;

import com.zup.casadocodigo.DiscountCoupon.DiscountCoupon;
import com.zup.casadocodigo.Location.Country;
import com.zup.casadocodigo.Location.State;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "purchase")
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

    @ManyToOne
    private State state;

    @NotBlank
    private String phone;

    @NotBlank
    private String cep;

    @NotNull
    private BigDecimal total;

    @NotNull
    private PurchaseStatus status;

    @NotNull
    @Size(min = 1)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "purchase_id")
    private List<PurchaseItem> items;

    @ManyToOne
    private DiscountCoupon discountCoupon;

    @Deprecated
    private Purchase() {
    }

    private Purchase(@NotBlank @Email String email, @NotBlank String name, @NotBlank String lastName,
                     @NotBlank String document, @NotBlank String address, @NotBlank String complement,
                     @NotBlank String city, @NotBlank Country country, State state, @NotBlank String phone,
                     @NotBlank String cep, @NotNull BigDecimal total, @NotNull @Size(min = 1) List<PurchaseItem> items,
                     @NotNull PurchaseStatus status) {
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
        Assert.notNull(new BigDecimal(0).compareTo(total) == 1, "Campo 'total' não pode ser nulo");
        Assert.isTrue(total.compareTo(new BigDecimal(0)) == 1, "Campo 'total' não pode ser menor que zero");
        Assert.isTrue(items.size() > 0, "Campo 'items' deve ser maior que zero");
        Assert.notNull(status, "Campo 'status' não pode estar em branco");

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
        this.total = total;
        this.items = items;
        this.status = status;
    }

    public BigDecimal getTotalCalculated() {
        BigDecimal total = this.items
                .stream()
                .map(item -> BigDecimal.valueOf(item.getQuantity()).multiply(item.getBook().getPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return total;
    }

    /**
     * Uma vez associado o cupom, uma compra nunca pode ter essa informação alterada.
     * O cupom só pode ser associado com uma compra que ainda não foi registrada no banco de dados
     */
    public void setDiscountCoupon(DiscountCoupon discountCoupon) {
        if (this.id != null || this.discountCoupon != null) {
            return;
        }

        this.discountCoupon = discountCoupon;
    }

    public void applyDiscountCoupom() {
        if (this.discountCoupon == null) {
            return;
        }

        BigDecimal percentage =  this.discountCoupon.getPercentage().divide(BigDecimal.valueOf(100));
        BigDecimal discountValue = this.total.multiply(percentage);

        this.total = this.total.subtract(discountValue);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public PurchaseStatus getStatus() {
        return status;
    }

    public void setStatus(PurchaseStatus status) {
        this.status = status;
    }

    public List<PurchaseItem> getItems() {
        return items;
    }

    public void setItems(List<PurchaseItem> items) {
        this.items = items;
    }

    public DiscountCoupon getDiscountCoupon() {
        return discountCoupon;
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
        private BigDecimal total;
        private List<PurchaseItem> items;
        private PurchaseStatus status;
        private DiscountCoupon discountCoupon;

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

        public Builder setTotal(BigDecimal total) {
            this.total = total;
            return this;
        }

        public Builder setItems(List<PurchaseItem> items) {
            this.items = items;
            return this;
        }

        public Builder setStatus(PurchaseStatus status) {
            this.status = status;
            return this;
        }

        public Builder setDiscountCoupon(DiscountCoupon discountCoupon) {
            this.discountCoupon = discountCoupon;
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
                    this.cep,
                    this.total,
                    this.items,
                    this.status
            );

            purchase.setDiscountCoupon(this.discountCoupon);

            return purchase;
        }
    }
}
