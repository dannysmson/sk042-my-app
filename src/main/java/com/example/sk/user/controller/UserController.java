package com.example.sk.user.controller;

import com.example.sk.user.entity.User;
import com.example.sk.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User", description = "유저 관리 API")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "회원 가입", description = "user객체를 입력해 회원가입")
    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        userService.signUp(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입이 완료되었습니다.");
    }

    @Operation(summary = "로그인", description = "username, password를 이용한 로그인")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        boolean result = userService.login(username, password);
        if (result) {
            return ResponseEntity.ok("로그인 성공.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패. 아이디 또는 비밀번호를 확인하세요.");
        }
    }

    @Operation(summary = "유저 정보 조회", description = "username을 이용해 해당 유저 정보 조회")
    @GetMapping("/{username}/my-info")
    public ResponseEntity<User> getMyInfo(@PathVariable String username) {
        User user = userService.getUserInfo(username);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
