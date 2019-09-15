package com.amol.microservices.product;

import com.amol.microservices.product.entity.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Amol Limaye
 **/
@RefreshScope
@RestController
public class ExternalConfig {

    @Value("${name}")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @GetMapping("/latest")
    public String getAllProducts(){
        return getName();
    }

}
