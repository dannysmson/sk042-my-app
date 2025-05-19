package com.example.sk.userStock.exception;

// 주식을 판매하려고 할 때 보유 수량보다 더 많은 수량을 요청할 때
public class InsufficientStockException extends RuntimeException {

    public InsufficientStockException(String message) {
        super(message);
    }
}