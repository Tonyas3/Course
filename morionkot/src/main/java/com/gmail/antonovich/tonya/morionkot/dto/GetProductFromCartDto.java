package com.gmail.antonovich.tonya.morionkot.dto;

public class GetProductFromCartDto {
    public String nameProduct;
    public Float selectedPrice;
    public String selectedWeight;
    public Integer quantity;

    public GetProductFromCartDto() {
    }

    public GetProductFromCartDto(String nameProduct, Float selectedPrice, String selectedWeight, Integer quantity) {
        this.nameProduct = nameProduct;
        this.selectedPrice = selectedPrice;
        this.selectedWeight = selectedWeight;
        this.quantity = quantity;
    }
}
