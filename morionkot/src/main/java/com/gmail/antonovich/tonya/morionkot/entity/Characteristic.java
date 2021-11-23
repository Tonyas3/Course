package com.gmail.antonovich.tonya.morionkot.entity;

import javax.persistence.*;

@Entity
@Table(name = "characteristic", schema = "public")
public class Characteristic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "price")
    private Float price;

    @Column(name = "weight")
    private String weight;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Characteristic() {
    }

    public Characteristic(Float price, String weight, Product product) {
        this.price = price;
        this.weight = weight;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product_id) {
        this.product = product_id;
    }
}
