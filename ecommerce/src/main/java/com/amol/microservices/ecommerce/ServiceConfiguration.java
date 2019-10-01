package com.amol.microservices.ecommerce;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Amol Limaye
 **/
@Configuration
public class ServiceConfiguration {

    @Bean
    public RestTemplate loadBalancedRestTemplate() {
        return new RestTemplate();
    }
}
