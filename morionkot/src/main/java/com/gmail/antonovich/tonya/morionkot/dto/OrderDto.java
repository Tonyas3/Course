package com.gmail.antonovich.tonya.morionkot.dto;

import java.util.Date;
import java.util.List;

public class OrderDto {

    public Long id;
    public Date delivery_date;
    public Float cost;
    public String delivery_address;
    public String phone_number;
    public String payment_method;
    public Boolean status;
    public String way_of_reception;
    public String customer_name;

    public OrderDto() {
    }

    public OrderDto(Long id, Date delivery_date, Float cost, String delivery_address, String phone_number, String payment_method, Boolean status, String way_of_reception, String customer_name) {
        this.id = id;
        this.delivery_date = delivery_date;
        this.cost = cost;
        this.delivery_address = delivery_address;
        this.phone_number = phone_number;
        this.payment_method = payment_method;
        this.status = status;
        this.way_of_reception = way_of_reception;
        this.customer_name = customer_name;
    }
}
