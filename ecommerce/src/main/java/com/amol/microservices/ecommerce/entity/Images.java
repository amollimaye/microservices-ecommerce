package com.amol.microservices.ecommerce.entity;

/**
 * @author Amol Limaye
 **/
public class Images {
    private Long Id;

    private int productId;

    private String image;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
