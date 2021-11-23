package com.gmail.antonovich.tonya.morionkot.dto;

import java.util.List;

public class AddOrderDto {
    public OrderDto order;
    public List<NewCartDto> cart;

    public AddOrderDto() {
    }

    public AddOrderDto(OrderDto order, List<NewCartDto> cart) {
        this.order = order;
        this.cart = cart;
    }
}
