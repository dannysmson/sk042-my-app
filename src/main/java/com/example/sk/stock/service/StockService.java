package com.example.sk.stock.service;
import com.example.sk.stock.exception.DuplicateStockException;
import com.example.sk.stock.exception.InvalidStockDataException;
import com.example.sk.stock.exception.StockNotFoundException;

import com.example.sk.stock.entity.Stock;
import com.example.sk.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    private final StockRepository stockRepository;

    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Stock createStock(Stock stock) {
        if (stockRepository.findByStockName(stock.getStockName()).isPresent()) {
            throw new DuplicateStockException("이미 존재하는 주식 이름입니다: " + stock.getStockName());
        }
        if (stock.getCurrentPrice() < 0) {
            throw new InvalidStockDataException("가격이나 수량은 음수가 될 수 없습니다.");
        }
        return stockRepository.save(stock);
    }

    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    public Stock getStockByName(String stockName) {
        return stockRepository.findByStockName(stockName)
                .orElseThrow(() -> new StockNotFoundException("해당 주식을 찾을 수 없습니다: " + stockName));
    }

    public void deleteStock(Long id) {
        if (!stockRepository.existsById(id)) {
            throw new StockNotFoundException("해당 ID의 주식을 찾을 수 없습니다: " + id);
        }
        stockRepository.deleteById(id);
    }
}
