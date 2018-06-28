package com.bbd.mvc.coffee.model.types;


import com.bbd.mvc.coffee.model.Coffee;

public class Arabica implements Coffee {

    @Override
    public void drink() {
        System.out.println("Arabica");
    }

    @Override
    public String toString(){
        return "Arabica";
    }

}
