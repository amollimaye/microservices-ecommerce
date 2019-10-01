package com.amol.microservices.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Amol Limaye
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class Image {
    private Long id;

    private int productId;

    private String path;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
