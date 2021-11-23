package com.gmail.antonovich.tonya.morionkot.dto;

public class CharacteristicDto {

    public Long id;
    public Float price;
    public String weight;

    public CharacteristicDto() {
    }

    public CharacteristicDto(Long id, Float price, String weight) {
        this.id = id;
        this.price = price;
        this.weight = weight;
    }
}
