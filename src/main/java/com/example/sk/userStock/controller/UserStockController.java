package com.example.sk.userStock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sk.userStock.service.UserStockService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/user-stock")
@Tag(name = "UserStock", description = "유저 주식 관리 API")
public class UserStockController {

    private final UserStockService userStockService;

    @Autowired
    public UserStockController(UserStockService userStockService) {
        this.userStockService = userStockService;
    }

    @Operation(summary = "사용자 보유 주식 조회", description = "username을 통한 사용자가 보유한 주식 리스트 조회")
    @GetMapping("/{username}")
    public ResponseEntity<?> getStockList(@PathVariable String username) {
        return ResponseEntity.ok(userStockService.getStockList(username));
    }

    @Operation(summary = "주식 구매", description = "주식 구매")
    @PostMapping("/buyStock")
    public ResponseEntity<?> buyStock(
            @RequestParam String username,
            @RequestParam Long stockId,
            @RequestParam int quantity) {

        userStockService.buyStock(username, stockId, quantity);
        return ResponseEntity.ok("주식 구매 완료");
    }

    @Operation(summary = "주식 판매", description = "주식 판매")
    @PostMapping("/sellStock")
    public ResponseEntity<?> sellStock(
            @RequestParam String username,
            @RequestParam Long stockId,
            @RequestParam int quantity) {

        userStockService.sellStock(username, stockId, quantity);
        return ResponseEntity.ok("주식 판매 완료");
    }
}
