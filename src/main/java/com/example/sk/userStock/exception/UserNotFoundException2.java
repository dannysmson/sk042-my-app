package com.example.sk.userStock.exception;

// 존재하지 않는 사용자를 조회하려 할 때
public class UserNotFoundException2 extends RuntimeException {
    public UserNotFoundException2(String message) {
        super(message);
    }
}
