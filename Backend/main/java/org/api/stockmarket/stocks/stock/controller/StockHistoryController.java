package org.api.stockmarket.stocks.stock.controller;

import lombok.AllArgsConstructor;
import org.api.stockmarket.stocks.stock.model.entity.StockHistory;
import org.api.stockmarket.stocks.stock.service.StockHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/history")
@AllArgsConstructor
public class StockHistoryController {

    @Autowired
    private final StockHistoryService stockHistoryService;

    @RequestMapping(value = "/stock/{ticker}")
    public List<StockHistory> getStockHistory(@PathVariable String ticker) {
        return stockHistoryService.findStockHistory(ticker);
    }
}
