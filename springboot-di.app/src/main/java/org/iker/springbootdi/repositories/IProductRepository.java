package org.iker.springbootdi.repositories;

import org.iker.springbootdi.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import java.util.List;

// Primary indica que tiene que inicializar este repositorio primero
@Primary
@Repository
public interface IProductRepository {
    List<Product> findAll();

    Product findById(Long id);
}
