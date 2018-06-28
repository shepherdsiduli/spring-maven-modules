package com.bbd.mvc.coffee.service;


import com.bbd.mvc.coffee.model.Coffee;
import com.bbd.mvc.coffee.model.types.Arabica;
import com.bbd.mvc.coffee.model.types.Robusta;

public class CoffeeMaker {

    public Coffee brewArabica() {
        return new Arabica();
    }

    public Coffee brewRobusta() {
        return new Robusta();
    }

}
