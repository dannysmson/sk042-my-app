package com.example.sk.stock.exception;

// 동일한 이름의 주식을 등록하려 할 때
public class DuplicateStockException extends RuntimeException {

    public DuplicateStockException(String message) {
        super(message);
    }
}