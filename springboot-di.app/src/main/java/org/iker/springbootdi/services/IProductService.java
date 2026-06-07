package org.iker.springbootdi.services;

import org.iker.springbootdi.models.Product;

import java.util.List;

public interface IProductService {

    List<Product> findAll();

    Product findById(Long id);
}
