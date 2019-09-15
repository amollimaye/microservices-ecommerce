package com.amol.microservices.ecommerce.assembler;

import com.amol.microservices.ecommerce.entity.EcommerceProduct;
import com.amol.microservices.ecommerce.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
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

    private static final String PRODUCT_SERVICE_ID = "/product-service";
    private static final String PRODUCT_SERVICE_ENDPOINT = "/products";

    private static final String IMAGE_SERVICE_ID = "image-service";

    public List<EcommerceProduct> getEcommerceProducts(){
        restTemplate.getForObject(getProductServiceURL(), Product.class);
        return null;
    }

    private String getProductServiceURL(){
        return getServiceUrl(PRODUCT_SERVICE_ID);
    }

    private String getServiceUrl(String serviceId) {
        List<ServiceInstance> list = discoveryClient.getInstances(serviceId);
        if (list != null && list.size() > 0 ) {
            URI uri =  list.get(0).getUri();
            return "http://"+uri.getHost() +
                    ":" +uri.getPort()
                    +list.get(0).getMetadata().get("contextPath")+
                    PRODUCT_SERVICE_ENDPOINT;
        }

        return null;
    }

//    public Optional<URI> serviceUrl() {
//        return discoveryClient.getInstances("myApp")
//                .stream()
//                .map(si -> si.getUri());
//          .findFirst()
//    }

}
