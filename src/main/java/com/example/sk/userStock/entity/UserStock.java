package com.example.sk.userStock.entity;

import com.example.sk.user.entity.User;
import com.example.sk.stock.entity.Stock;

import jakarta.persistence.*;

@Entity
@Table(name = "user_stock")
public class UserStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;

    private int quantity;

    public UserStock() {}

    public UserStock(User user, Stock stock, int quantity) {
        this.user = user;
        this.stock = stock;
        this.quantity = quantity;
    }

    // Getter & Setter
    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
