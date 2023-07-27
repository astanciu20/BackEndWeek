package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class ProductController {

    private final ProductRepository repository;

    ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/products")
    List<Product> all() {
        return repository.findAll();
    }

    @PostMapping("/products")
    Product newProduct(@RequestBody Product newProduct) {
        return repository.save(newProduct);
    }

    @GetMapping("/product/{id}")
    Product one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @PutMapping("/product/{id}")
    Product replaceProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        return repository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setPrice(newProduct.getPrice());
                    return repository.save(product);
                })
                .orElseGet(() -> {
                    newProduct.setId(id);
                    return repository.save(newProduct);
                });
    }

    @DeleteMapping("/product/{id}")
    void deleteProduct(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
