package com.example.demo;

import lombok.Data;

@Data
public class ProductByIdDTO {
    private Long id;
    private String name;
    private Double price;
    private int stock;

    ProductByIdDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.stock = product.getStock().getStock();
    }
}
