package com.gmail.antonovich.tonya.morionkot.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order", schema = "public")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "delivery_date")
    private Date delivery_date;

    @Column(name = "cost")
    private Float cost;

    @Column(name = "delivery_address")
    private String delivery_address;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "payment_method")
    private String payment_method;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "way_of_reception")
    private String way_of_reception;

    @Column(name = "customer_name")
    private String customer_name;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> carts;

    public Order() {
    }

    public Order(Date delivery_date, Float cost, String delivery_address, String phone_number, String payment_method, Boolean status, String way_of_reception, String customer_name, List<Cart> carts) {
        this.delivery_date = delivery_date;
        this.cost = cost;
        this.delivery_address = delivery_address;
        this.phone_number = phone_number;
        this.payment_method = payment_method;
        this.status = status;
        this.way_of_reception = way_of_reception;
        this.customer_name = customer_name;
        this.carts = carts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(Date delivery_date) {
        this.delivery_date = delivery_date;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public String getDelivery_address() {
        return delivery_address;
    }

    public void setDelivery_address(String delivery_adress) {
        this.delivery_address = delivery_adress;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getWay_of_reception() {
        return way_of_reception;
    }

    public void setWay_of_reception(String way_of_reception) {
        this.way_of_reception = way_of_reception;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }
}
