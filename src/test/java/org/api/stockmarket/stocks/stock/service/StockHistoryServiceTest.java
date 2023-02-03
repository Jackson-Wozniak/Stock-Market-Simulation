package org.api.stockmarket.stocks.stock.service;

import org.api.stockmarket.stocks.stock.model.entity.StockHistory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class StockHistoryServiceTest {

    @Autowired
    private StockHistoryService stockHistoryService;

    @Test
    void stockHistoryOnlyHappensOnThirdMonths(){
        List<StockHistory> stockHistory = stockHistoryService.findStockHistoryByTicker("GOOG");
        stockHistory.forEach(Assertions::assertNotNull);
    }

}