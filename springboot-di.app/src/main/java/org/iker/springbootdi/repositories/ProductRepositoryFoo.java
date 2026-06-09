package org.iker.springbootdi.repositories;

import org.iker.springbootdi.models.Product;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository("productFoo")
public class ProductRepositoryFoo implements IProductRepository{
    @Override
    public List<Product> findAll() {
        return Collections.singletonList(new Product(123L, "Alfombrilla", 12L));
    }

    @Override
    public Product findById(Long id) {
        return new Product(id, "Alfombrilla", 12L);
    }
}
