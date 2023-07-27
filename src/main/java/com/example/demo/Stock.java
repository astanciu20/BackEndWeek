package com.example.demo;

import jakarta.persistence.*;

@Entity
@Table(name = "products_stocks")
public class Stock {

    @Id
    @Column(name = "product_id")
    private Long id;
    private int stock;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

