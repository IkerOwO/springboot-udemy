package org.iker.springbootdi.repositories;

import org.iker.springbootdi.models.Product;
import org.springframework.stereotype.Repository;
import java.util.Arrays;
import java.util.List;

// NO se utilizan clases, se utilizan Interfaces para los repositories
@Repository
public class ProductRepositoryImpl implements IProductRepository{

    private List<Product> data;

    public ProductRepositoryImpl() {
        this.data = Arrays.asList(
                new Product(1L, "Teclado", 12L),
                new Product(2L, "Raton Gaming", 30L),
                new Product(3L, "Auriculares", 50L),
                new Product(4L, "Monitor Gaming", 100L)
        );
    }

    // @Override porque estamos sobreescribiendo los metodos que se encuentran en el repositorio
    @Override
    public List<Product> findAll(){
        return data;
    }

    @Override
    public Product findById(Long id){
        return data.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }
}
