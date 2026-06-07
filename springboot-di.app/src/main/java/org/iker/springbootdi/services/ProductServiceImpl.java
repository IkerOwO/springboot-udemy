package org.iker.springbootdi.services;

import org.iker.springbootdi.models.Product;
import org.iker.springbootdi.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

// El service accede a los datos mediante el Repository (puede modificar, hacer calculos...)
@Service
public class ProductServiceImpl implements IProductService {

    private IProductRepository repository;

    @Autowired
    public void setRepository(IProductRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Product> findAll(){
        return repository.findAll().stream().map(p -> {
            Double priceImp = p.getPrice() - (p.getPrice() * 0.21d);
            // Utilizamos el metodo 'clone' que hemos creado en el model "Product"
            Product newProd = (Product) p.clone();
            newProd.setPrice(priceImp.longValue());
            return newProd;
        }).collect(Collectors.toList());
    }
    @Override
    public Product findById(Long id){
        return repository.findById(id);
    }

}
