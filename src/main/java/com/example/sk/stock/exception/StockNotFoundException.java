package com.example.sk.stock.exception;

// 특정 주식을 조회할 때 존재하지 않는 경우
public class StockNotFoundException extends RuntimeException {

    public StockNotFoundException(String message) {
        super(message);
    }
}
