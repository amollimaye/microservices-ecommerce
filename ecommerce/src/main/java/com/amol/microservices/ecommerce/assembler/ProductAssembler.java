package com.amol.microservices.ecommerce.assembler;

import com.amol.microservices.ecommerce.config.ExternalConfig;
import com.amol.microservices.ecommerce.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Amol Limaye
 **/
@Component
public class ProductAssembler {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ExternalConfig externalConfig;

    private static final String PRODUCT_SERVICE_ID = "product-service";
    private static final String PRODUCT_SERVICE_ENDPOINT = "/product-service/products";

    private static final String IMAGE_SERVICE_ID = "image-service";
    private static final String IMAGE_SERVICE_ENDPOINT = "/image-service/images";

    public List<EcommerceProduct> getEcommerceProducts(){
        ResponseEntity<ProductResponse> productResponse = restTemplate.exchange(getServiceURI(PRODUCT_SERVICE_ID,PRODUCT_SERVICE_ENDPOINT), HttpMethod.GET,null,ProductResponse.class);
        ResponseEntity<ImageResponse> imageResponse = null;
        if(externalConfig.getUseImages()) {
            imageResponse = restTemplate.exchange(getServiceURI(IMAGE_SERVICE_ID, IMAGE_SERVICE_ENDPOINT), HttpMethod.GET, null, ImageResponse.class);
        }
        return mergeProductData(productResponse,imageResponse);
    }

    private URI getServiceURI(String serviceId,String serviceEndpoint) {
        URI uri = discoveryClient.getInstances(serviceId).stream().map
                (instance -> instance.getUri()).findFirst()
                .map(s -> s.resolve(serviceEndpoint)).get();
        return uri;
    }

    private List<EcommerceProduct> mergeProductData(ResponseEntity<ProductResponse> productResponse, ResponseEntity<ImageResponse> imageResponse){
        List<EcommerceProduct> ecommerceProducts = new ArrayList<>();
        for(Product product:productResponse.getBody().getProducts()){
            EcommerceProduct ecommerceProduct = new EcommerceProduct(product);
            if(imageResponse!=null) {
                Image image = imageResponse.getBody().getImages().
                        stream().filter(i -> product.getProductId() == i.getProductId())
                        .findAny().orElse(null);
                ecommerceProduct.setImage(image);
            }
            ecommerceProducts.add(ecommerceProduct);
        }
        return ecommerceProducts;
    }
}
