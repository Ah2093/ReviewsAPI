package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.model.Product;
import com.udacity.course3.reviews.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

/**
 * Spring REST controller for working with product entity.
 */
@RestController
@RequestMapping("/products")
public class ProductsController {

    // TODO: Wire JPA repositories here or service
    private final ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Creates a product.
     * <p>
     * 1. Accept product as argument. Use {@link RequestBody} annotation.
     * 2. Save product.
     *
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody Product product) {
        try {
             productService.saveProduct(product);
        } catch (Exception exception) {
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Finds a product by id.
     *
     * @param id The id of the product.
     * @return The product if found, or a 404 not found.
     */
    @GetMapping("/{id}")

    public ResponseEntity<?> findById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(productService.getProductById(id));
        } catch (Exception exception) {
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Lists all products.
     *
     * @return The list of products.
     */
    @GetMapping
    public List<?> listProducts() {
        try {
            return ResponseEntity.ok(productService.getProducts()).getBody();
        } catch (Exception exception) {
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
        }
    }
}