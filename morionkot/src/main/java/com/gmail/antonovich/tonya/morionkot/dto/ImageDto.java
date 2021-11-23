package com.gmail.antonovich.tonya.morionkot.dto;

public class ImageDto {

    public Long id;
    public String name;

    public ImageDto() {
    }

    public ImageDto(String name) {
        this.name = name;
    }

    public ImageDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
