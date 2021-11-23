package com.gmail.antonovich.tonya.morionkot.exeption;

public class NoSuchEntityException extends Exception{
    public NoSuchEntityException() {
    }

    public NoSuchEntityException(String message) {
        super(message);
    }
}
