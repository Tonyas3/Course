package com.gmail.antonovich.tonya.morionkot.dto;

import java.util.List;

public class ProductDto {

    public Long id;
    public String name;
    public String description;
    public List<CharacteristicDto> characteristics;
    public List<ImageDto> images;
    public CategoryDto category;

    public ProductDto() {
    }

    public ProductDto(Long id, String name, String description, List<CharacteristicDto> characteristics, List<ImageDto> images, CategoryDto category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.characteristics = characteristics;
        this.images = images;
        this.category = category;
    }
}
