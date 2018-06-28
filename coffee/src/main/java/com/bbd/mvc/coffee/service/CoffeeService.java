package com.bbd.mvc.coffee.service;

import org.springframework.stereotype.Service;

@Service
public class CoffeeService {
    public String getCoffee() {
        if(Math.random() > 0.5){
            return new CoffeeMaker().brewArabica().toString();
        }else{
            return new CoffeeMaker().brewRobusta().toString();
        }
    }
}
