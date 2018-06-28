package com.bbd.mvc.coffee.api;

import com.bbd.mvc.coffee.model.CoffeeResponse;
import com.bbd.mvc.coffee.service.CoffeeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/coffee")
@RestController
public class CoffeeController {

    private final CoffeeService metricsService;

    public CoffeeController(CoffeeService metricsService) {
        this.metricsService = metricsService;
    }

    @RequestMapping("/coffee")
    public CoffeeResponse metrics() {
        return new CoffeeResponse(metricsService.getCoffee());
    }
}
