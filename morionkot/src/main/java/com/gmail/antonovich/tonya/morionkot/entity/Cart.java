package com.gmail.antonovich.tonya.morionkot.entity;

import javax.persistence.*;

@Entity
@Table(name = "cart", schema = "public")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "selected_price")
    private Float selectedPrice;

    @Column(name = "selected_weight")
    private String selectedWeight;

    @Column(name = "quantity")
    private Integer quantity;

    public Cart() {
    }

    public Cart(Long id, Order order, Product product, Float selectedPrice, String selectedWeight, Integer quantity) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.selectedPrice = selectedPrice;
        this.selectedWeight = selectedWeight;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Float getSelectedPrice() {
        return selectedPrice;
    }

    public void setSelectedPrice(Float selectedPrice) {
        this.selectedPrice = selectedPrice;
    }

    public String getSelectedWeight() {
        return selectedWeight;
    }

    public void setSelectedWeight(String selectedWeight) {
        this.selectedWeight = selectedWeight;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order_id) {
        this.order = order_id;
    }
}
