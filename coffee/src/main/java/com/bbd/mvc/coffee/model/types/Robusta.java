package com.bbd.mvc.coffee.model.types;

import com.bbd.mvc.coffee.model.Coffee;

public class Robusta implements Coffee {

    @Override
    public void drink() {
        System.out.println("Robusta");
    }

    @Override
    public String toString(){
        return "Robusta";
    }
}
