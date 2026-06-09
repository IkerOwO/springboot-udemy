package org.iker.springbootdi.repositories;

import org.iker.springbootdi.models.Product;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ProductRepositoryJson implements IProductRepository{

    private List<Product> list;

    public ProductRepositoryJson() throws IOException {
        Resource resource = new ClassPathResource("product.json");
        readValueJson(resource);
    }

    public ProductRepositoryJson(Resource resource) throws IOException {
        readValueJson(resource);
    }

    private void readValueJson(Resource resource) throws IOException {
        // Convertir un archivo a un objeto de java
        ObjectMapper objectMapper = new ObjectMapper();
        list = Arrays.asList(objectMapper.readValue(resource.getInputStream(), Product[].class));
    }

    @Override
    public List<Product> findAll() {
        return list;
    }

    @Override
    public Product findById(Long id) {
        return list.stream().filter(p -> {
            return p.getId().equals(id);
        }).findFirst().orElseThrow();
    }
}
