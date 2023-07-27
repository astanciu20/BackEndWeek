package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
class ProductController {

    private final ProductRepository productRepository;

    private final StockRepository stockRepository;

    ProductController(ProductRepository productRepository, StockRepository stockRepository) {
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
    }


    @GetMapping("/products")
    List<ProductAllDTO> all() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> {
                    return new ProductAllDTO(product);
                })
                .toList();
    }

    @PostMapping("/products")
    Product newProduct(@RequestBody Product newProduct) {
        newProduct = productRepository.save(newProduct);
        Stock stock = new Stock();
        stock.setStock(0);
        stock.setId(newProduct.getId());
        stockRepository.save(stock);
        return newProduct;
    }

    @GetMapping("/product/{id}")
    ProductByIdDTO one(@PathVariable Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return new ProductByIdDTO(product);
    }

    @PutMapping("/product/{id}")
    Optional<Stock> replaceStock(@RequestBody Stock newStock, @PathVariable Long id) {
        return stockRepository.findById(id)
                .map(stock -> {
                    stock.setStock(newStock.getStock());
                    return stockRepository.save(stock);
                });
    }

    @DeleteMapping("/product/{id}")
    void deleteProduct(@PathVariable Long id) {
        stockRepository.deleteById(id);
        productRepository.deleteById(id);
    }
}
