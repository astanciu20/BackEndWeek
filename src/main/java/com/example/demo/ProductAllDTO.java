package com.example.demo;

import lombok.Data;
import lombok.Getter;

@Data
public class ProductAllDTO {
    private Long id;
    private String name;
    private Double price;

    ProductAllDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
    }
}
