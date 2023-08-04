package com.udacity.course3.reviews.service;

import com.udacity.course3.reviews.model.Product;
import com.udacity.course3.reviews.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepo productRepo ;
    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Product saveProduct(Product product)
    {
        return productRepo.save(product);
    }
    public Product getProductById(long id)
    {
        return productRepo.findById(id).get();
    }

    public List<Product> getProducts()
    {
        return (List<Product>) productRepo.findAll();
    }
}
