package com.gmail.antonovich.tonya.morionkot.dto;

public class CategoryDto {

    public Long id;
    public String name;

    public CategoryDto() {
    }

    public CategoryDto(String name) {
        this.name = name;
    }

    public CategoryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
