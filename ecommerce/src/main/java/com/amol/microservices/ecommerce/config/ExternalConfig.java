package com.amol.microservices.ecommerce.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author Amol Limaye
 **/
@RefreshScope
@Component
public class ExternalConfig {

    @Value("${useImages}")
    private String useImages;

    public ExternalConfig(){
        System.out.println(this.useImages);
    }
    public boolean getUseImages() {
        return Boolean.getBoolean(useImages);
    }

    public void setUseImages(String useImages) {
        this.useImages = useImages;
    }
}
