package com.sbev.elastic.poc.web;

import com.sbev.elastic.poc.data.Product;
import com.sbev.elastic.poc.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class WebController {

    private final ProductService productService;

    public WebController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Flux<Product> getAllProducts() {
        return Flux.fromIterable(this.productService.getProducts());
    }

    @GetMapping("/{id}")
    public Mono<Product> getProductById(@PathVariable("id") String id) {
        return Mono.fromCallable(() -> this.productService.getProductById(id))
                .flatMap(optional -> optional.map(Mono::just).orElseGet(Mono::empty));
    }
}
