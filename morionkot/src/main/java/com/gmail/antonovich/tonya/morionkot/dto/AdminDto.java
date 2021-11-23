package com.gmail.antonovich.tonya.morionkot.dto;

public class AdminDto {

    public String login;
    public String password;

    public AdminDto() {
    }

    public AdminDto(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
