module com.bbd.mvc {
    requires java.sql;
    requires spring.context;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires modules;
    requires coffee;

    exports com.bbd.mvc;
}