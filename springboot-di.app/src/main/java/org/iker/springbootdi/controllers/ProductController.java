package org.iker.springbootdi.controllers;

import org.iker.springbootdi.models.Product;
import org.iker.springbootdi.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    // Llamamos con @Autowired al @Component IProductServiceImpl
    @Autowired
    private IProductService service;

    @GetMapping
    public List<Product> list(){
        return service.findAll();
    }

    // Buscar por el id
    @GetMapping("/{id}")
    public Product show(@PathVariable Long id){
        return service.findById(id);
    }
}
