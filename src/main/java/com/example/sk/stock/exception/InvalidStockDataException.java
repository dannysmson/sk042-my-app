package com.example.sk.stock.exception;

// 주식 등록이나 수정 시, 입력 데이터가 잘못된 경우 (가격이 음수인 경우)
public class InvalidStockDataException extends RuntimeException {

    public InvalidStockDataException(String message) {
        super(message);
    }
}
