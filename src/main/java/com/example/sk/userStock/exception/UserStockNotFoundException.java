package com.example.sk.userStock.exception;

// 유저가 보유하지 않은 주식을 조회하려고 할 때
public class UserStockNotFoundException extends RuntimeException {

    public UserStockNotFoundException(String message) {
        super(message);
    }
}