package com.gmail.antonovich.tonya.morionkot.dto;

public class CommentsDto {

    public Long id;
    public String username;
    public String comment;

    public CommentsDto() {
    }

    public CommentsDto(Long id, String username, String comment) {
        this.id = id;
        this.username = username;
        this.comment = comment;
    }
}
