module coffee {
    requires spring.web;
    requires spring.beans;
    requires spring.context;

    exports com.bbd.mvc.coffee.model;
    exports com.bbd.mvc.coffee.service;
    exports com.bbd.mvc.coffee.api;
}