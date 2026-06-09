package org.iker.springbootdi.services;

import org.iker.springbootdi.models.Product;
import org.iker.springbootdi.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

// El service accede a los datos mediante el Repository (puede modificar, hacer calculos...)
@Service
public class ProductServiceImpl implements IProductService {

    //@Autowired
    //@Qualifier("productList") // El @Qualifier inyecta el componente por su nombre (Hay que ponerlo siempre en minuscula)
    private IProductRepository repository;

    @Autowired
    private Environment environment;

    @Autowired
    public void setRepository(@Qualifier("productRepositoryJson") IProductRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Product> findAll(){
        return repository.findAll().stream().map(p -> {

            System.out.println(environment.getProperty("config.price.tax", Double.class));
            Double priceImp = p.getPrice() - (p.getPrice() * environment.getProperty("config.price.tax", Double.class));

            // Utilizamos el metodo 'clone' que hemos creado en el model "Product"
            //Product newProd = (Product) p.clone();
            //newProd.setPrice(priceImp.longValue());

            p.setPrice(priceImp.longValue());
            return p;
            //return newProd;
        }).collect(Collectors.toList());
    }
    @Override
    public Product findById(Long id){
        return repository.findById(id);
    }

}
