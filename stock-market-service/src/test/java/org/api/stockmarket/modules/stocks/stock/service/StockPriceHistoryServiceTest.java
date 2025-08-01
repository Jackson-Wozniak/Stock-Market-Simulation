package org.api.stockmarket.modules.stocks.stock.service;

import org.api.stockmarket.modules.stocks.entity.StockPriceHistory;
import org.api.stockmarket.modules.stocks.service.StockPriceHistoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class StockPriceHistoryServiceTest {

    @Autowired
    private StockPriceHistoryService stockPriceHistoryService;

    @Test
    void stockHistoryOnlyHappensOnThirdMonths(){
        List<StockPriceHistory> stockPriceHistory = stockPriceHistoryService.findStockHistoryByTicker("GOOG");
        stockPriceHistory.forEach(Assertions::assertNotNull);
    }

}