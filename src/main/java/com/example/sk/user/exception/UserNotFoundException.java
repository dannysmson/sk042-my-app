package com.example.sk.user.exception;

// 존재하지 않는 사용자를 조회하려 할 때
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
