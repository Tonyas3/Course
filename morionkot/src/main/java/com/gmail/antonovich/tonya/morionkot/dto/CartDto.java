package com.gmail.antonovich.tonya.morionkot.dto;

public class CartDto {

    public ProductDto product;
    public OrderDto order;

    public CartDto() {
    }

    public CartDto(ProductDto product, OrderDto order) {
        this.product = product;
        this.order = order;
    }
}
