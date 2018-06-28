package com.bbd.mvc.coffee.model;

public class CoffeeResponse {
    private String coffee;

    public CoffeeResponse(String coffee) {
        this.coffee = coffee;
    }

    public String getCoffee() {
        return coffee;
    }
}
