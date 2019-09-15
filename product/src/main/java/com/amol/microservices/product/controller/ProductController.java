package com.amol.microservices.product.controller;

import com.amol.microservices.product.entity.Product;
import com.amol.microservices.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Amol Limaye
 **/
@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

//    @Autowired
//    ExternalConfig externalConfig;

    @GetMapping("/products")
    public List<Product> getAllProducts(){
//        System.out.println(externalConfig.getName());
        return productRepository.findAll();
    }
}
