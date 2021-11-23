package com.gmail.antonovich.tonya.morionkot.dto;

public class NewCartDto {
    public Long id;
    public Float selectedPrice;
    public String selectedWeight;
    public Integer quantity;

    public NewCartDto() {
    }

    public NewCartDto(Long id, Float selectedPrice, String selectedWeight, Integer quantity) {
        this.id = id;
        this.selectedPrice = selectedPrice;
        this.selectedWeight = selectedWeight;
        this.quantity = quantity;
    }
}
