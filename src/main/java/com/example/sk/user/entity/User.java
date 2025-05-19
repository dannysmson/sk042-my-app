package com.example.sk.user.entity;

import jakarta.persistence.*;
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Double playerMoney;

    // 기본 생성자
    public User() {
    }

    // 생성자
    public User(String username, String password, Double playerMoney) {
        this.username = username;
        this.password = password;
        this.playerMoney = playerMoney;
    }

    // Getter 및 Setter
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getPlayerMoney() {
        return playerMoney;
    }

    public void setPlayerMoney(Double playerMoney) {
        this.playerMoney = playerMoney;
    }
}
