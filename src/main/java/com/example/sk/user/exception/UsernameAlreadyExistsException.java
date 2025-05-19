package com.example.sk.user.exception;

// 이미 존재하는 사용자명을 사용하려 할 때
public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}
