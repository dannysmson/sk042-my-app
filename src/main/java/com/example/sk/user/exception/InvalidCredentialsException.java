package com.example.sk.user.exception;

// 로그인 시 아이디나 비밀번호가 일치하지 않을 때 발생하는 예외
public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
