package com.example.sk.userStock.service;

import com.example.sk.userStock.entity.UserStock;
import com.example.sk.userStock.repository.UserStockRepository;
import com.example.sk.user.entity.User;
import com.example.sk.user.repository.UserRepository;
import com.example.sk.stock.entity.Stock;
import com.example.sk.stock.repository.StockRepository;
import com.example.sk.userStock.exception.InsufficientStockException;
import com.example.sk.userStock.exception.UserStockNotFoundException;
import com.example.sk.userStock.exception.InsufficientFundsException;
import com.example.sk.userStock.exception.UserNotFoundException2;
import com.example.sk.userStock.exception.StockNotFoundException2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserStockService {

    private final UserStockRepository userStockRepository;
    private final UserRepository userRepository;
    private final StockRepository stockRepository;

    @Autowired
    public UserStockService(UserStockRepository userStockRepository, UserRepository userRepository, StockRepository stockRepository) {
        this.userStockRepository = userStockRepository;
        this.userRepository = userRepository;
        this.stockRepository = stockRepository;
    }

    public List<UserStock> getStockList(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다: " + username));
        
        return userStockRepository.findByUser(user);
    }

    public void buyStock(String username, Long stockId, int quantity) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException2("해당 유저를 찾을 수 없습니다: " + username));
        
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new StockNotFoundException2("해당 주식을 찾을 수 없습니다: " + stockId));
        
        Double totalCost = stock.getCurrentPrice() * quantity;

        if (user.getPlayerMoney() < totalCost) {
            throw new InsufficientFundsException("보유 금액이 부족합니다. 필요한 금액: " + totalCost + ", 보유 금액: " + user.getPlayerMoney());
        }

        user.setPlayerMoney(user.getPlayerMoney() - totalCost);
        userRepository.save(user);

        Optional<UserStock> existingStock = userStockRepository.findByUserAndStock(user, stock);

        if (existingStock.isPresent()) {
            UserStock userStock = existingStock.get();
            userStock.setQuantity(userStock.getQuantity() + quantity);
            userStockRepository.save(userStock);
        } else {
            UserStock newUserStock = new UserStock(user, stock, quantity);
            userStockRepository.save(newUserStock);
        }
    }

    public void sellStock(String username, Long stockId, int quantity) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException2("해당 유저를 찾을 수 없습니다: " + username));
        
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new StockNotFoundException2("해당 주식을 찾을 수 없습니다: " + stockId));

        UserStock userStock = userStockRepository.findByUserAndStock(user, stock)
                .orElseThrow(() -> new UserStockNotFoundException("해당 유저가 보유하지 않은 주식입니다."));

        if (userStock.getQuantity() < quantity) {
            throw new InsufficientStockException("판매하려는 수량이 보유 수량보다 많습니다.");
        }

        int newQuantity = userStock.getQuantity() - quantity;
        Double totalEarned = stock.getCurrentPrice() * quantity;

        user.setPlayerMoney(user.getPlayerMoney() + totalEarned);
        userRepository.save(user);

        if (newQuantity == 0) {
            userStockRepository.delete(userStock);
        } else {
            userStock.setQuantity(newQuantity);
            userStockRepository.save(userStock);
        }
    }
}
