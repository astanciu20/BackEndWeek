package com.example.demo;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super("Nu am gasit produsul cu id: " + id);
    }
}
