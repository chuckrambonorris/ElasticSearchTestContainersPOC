package com.sbev.elastic.poc.service;

import com.sbev.elastic.poc.dao.ProductRepository;
import com.sbev.elastic.poc.data.Product;
import com.sbev.elastic.poc.data.ProductInput;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryMapping
    public Iterable<Product> getProducts() {
        return productRepository.findAll();
    }

    @MutationMapping
    public Product createProduct(@Argument ProductInput product) {
        Product p = new Product(product.id(), product.name(), product.category(), product.price());
        return productRepository.save(p);
    }

    @QueryMapping
    public Optional<Product> getProductById(String productId) {
        return productRepository.findById(productId);
    }
}
