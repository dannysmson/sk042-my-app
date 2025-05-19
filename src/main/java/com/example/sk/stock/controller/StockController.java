package com.example.sk.stock.controller;

import com.example.sk.stock.entity.Stock;
import com.example.sk.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
@Tag(name = "Stock", description = "주식 관리 API")
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @Operation(summary = "주식 생성", description = "stock객체를 이용해 새로운 주식 생성")
    @PostMapping("/create")
    public ResponseEntity<Stock> createStock(@RequestBody Stock stock) {
        Stock createdStock = stockService.createStock(stock);
        return new ResponseEntity<>(createdStock, HttpStatus.CREATED);
    }

    @Operation(summary = "주식 조회", description = "등록 되어 있는 모든 주식 리스트 조회")
    @GetMapping("/all")
    public ResponseEntity<List<Stock>> getAllStocks() {
        List<Stock> stocks = stockService.getAllStocks();
        return new ResponseEntity<>(stocks, HttpStatus.OK);
    }

    @Operation(summary = "특정 주식 조회", description = "주식 이름으로 주식 조회")
    @GetMapping("/{stockName}")
    public ResponseEntity<Stock> getStockByName(@PathVariable String stockName) {
        Stock stock = stockService.getStockByName(stockName);
        return new ResponseEntity<>(stock, HttpStatus.OK);
    }

    @Operation(summary = "주식 삭제", description = "id를 통해 주식 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
