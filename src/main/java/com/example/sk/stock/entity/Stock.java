package com.example.sk.stock.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String stockName;

    @Column(nullable = false)
    private Double currentPrice;

    public Stock() {}

    public Stock(String stockName, Double currentPrice) {
        this.stockName = stockName;
        this.currentPrice = currentPrice;
    }

    public Long getId() {
        return id;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }
}
