package org.iker.springbootdi;

import org.iker.springbootdi.repositories.IProductRepository;
import org.iker.springbootdi.repositories.ProductRepositoryJson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {

    //@Value("classpath:product.json")
    //private Resource resource;

    @Bean
    IProductRepository productRepositoryJson() throws IOException {
        return new ProductRepositoryJson();
    }

}
