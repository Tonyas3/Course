package com.gmail.antonovich.tonya.morionkot.dto;

import java.util.List;

public class NewProductDto {
    public String name;
    public List<Float> price;
    public List<String> weight;
    public String description;
    public List<String> image;
    public String category;

    public NewProductDto() {
    }

    public NewProductDto(String name, List<Float> price, List<String> weight, String description, List<String> image, String category) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.description = description;
        this.image = image;
        this.category = category;
    }
}
