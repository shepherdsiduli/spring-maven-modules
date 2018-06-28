package com.bbd.mvc.coffee.api;

import com.bbd.mvc.coffee.model.CoffeeResponse;
import com.bbd.mvc.coffee.service.CoffeeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/coffee")
@RestController
public class CoffeeController {

    private final CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @RequestMapping("/coffee")
    public CoffeeResponse coffee() {
        return new CoffeeResponse(coffeeService.getCoffee());
    }
}
