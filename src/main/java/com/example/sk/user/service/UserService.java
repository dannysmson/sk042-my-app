package com.example.sk.user.service;

import com.example.sk.user.entity.User;
import com.example.sk.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sk.user.exception.UsernameAlreadyExistsException;
import com.example.sk.user.exception.InvalidCredentialsException;
import com.example.sk.user.exception.UserNotFoundException;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 회원가입
    public void signUp(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("이미 존재하는 사용자명입니다.");
        }
        userRepository.save(user);
    }
    // 로그인
    public boolean login(String username, String password) {
        User user = userRepository.findByUsername(username)
                                .orElseThrow(() -> new InvalidCredentialsException("아이디 또는 비밀번호가 일치하지 않습니다."));
        if (!user.getPassword().equals(password)) {
            throw new InvalidCredentialsException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
        return true;
    }

    // 내 정보 조회
    public User getUserInfo(String username) {
        return userRepository.findByUsername(username)
                         .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));
    }
}
