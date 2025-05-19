package com.example.sk.userStock.exception;

// 특정 주식을 조회할 때 존재하지 않는 경우
public class StockNotFoundException2 extends RuntimeException {

    public StockNotFoundException2(String message) {
        super(message);
    }
}
