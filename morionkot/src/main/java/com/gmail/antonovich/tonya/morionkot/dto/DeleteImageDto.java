package com.gmail.antonovich.tonya.morionkot.dto;

public class DeleteImageDto {
    public String imageName;
    public Long idProduct;

    public DeleteImageDto() {
    }

    public DeleteImageDto(String imageName, Long idProduct) {
        this.imageName = imageName;
        this.idProduct = idProduct;
    }
}
