package com.amol.microservices.ecommerce.entity;

/**
 * @author Amol Limaye
 **/
public class EcommerceProduct {

    Product product;

    Images images;

    public EcommerceProduct(Product product){
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

}
