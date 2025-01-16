package com.fly.es.controller;

import com.fly.es.app.Product;
import com.fly.es.app.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hello")
@Slf4j
@RequiredArgsConstructor
public class HelloController {
    private final ProductService productService;

    @GetMapping("/say")
    public String sayHello() {
        System.out.println("hello");
        return "hello";
    }

    @GetMapping("/log")
    public String logExample() {
        log.info("Info level log");
        log.debug("Debug level log");
        log.error("Error level log");
        return "Logging example!";
    }

    @GetMapping("/add/{count}")
    public String addProduct(@PathVariable(name = "count") Integer count) throws Exception {
        if (count <= 0) {
            throw new Exception("count must be greater than 0");
        }
        productService.saveProduct(count);
        return String.valueOf(count);
    }

    @GetMapping("/get/{name}")
    public List<Product> getProduct(@PathVariable(name = "name") String name) throws Exception {
        return productService.getProduct(name);
    }

    @GetMapping("/update/{name}")
    public String updateProduct(@PathVariable(name = "name") String name) throws Exception {
        productService.updateProduct(name);
        return "ok";
    }
}
