package com.example.sk.userStock.repository;

import com.example.sk.userStock.entity.UserStock;
import com.example.sk.user.entity.User;
import com.example.sk.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserStockRepository extends JpaRepository<UserStock, Long> {

    List<UserStock> findByUser(User user);
    
    Optional<UserStock> findByUserAndStock(User user, Stock stock);
}
