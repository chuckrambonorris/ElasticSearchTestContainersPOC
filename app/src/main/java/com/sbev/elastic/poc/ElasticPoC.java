package com.sbev.elastic.poc;

import com.sbev.elastic.poc.dao.ProductRepository;
import com.sbev.elastic.poc.data.Product;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ElasticPoC {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ElasticPoC.class).run(args);
    }

    @Bean
    ApplicationRunner run(ProductRepository productRepository) {
        return args -> {
            productRepository.save(new Product("1", "iPhone 12", "Smartphone", 799.99));
            productRepository.save(new Product("2", "MacBook Pro", "Laptop", 1299.99));
            productRepository.save(new Product("3", "Apple Watch", "Smartwatch", 399.99));
        };
    }
}
