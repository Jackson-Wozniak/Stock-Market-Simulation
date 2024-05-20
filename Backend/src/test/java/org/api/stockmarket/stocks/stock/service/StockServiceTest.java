package org.api.stockmarket.stocks.stock.service;

import org.api.stockmarket.stocks.stock.defaults.DefaultStocksList;
import org.api.stockmarket.stocks.stock.enums.InvestorRating;
import org.api.stockmarket.stocks.stock.enums.MarketCap;
import org.api.stockmarket.stocks.stock.enums.Volatility;
import org.api.stockmarket.stocks.stock.exception.StockNotFoundException;
import org.api.stockmarket.stocks.stock.entity.Stock;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class StockServiceTest {

    @Autowired
    private StockService stockService;

    @Test
    void defaultStocksExist(){
        assertDoesNotThrow(() -> stockService.getStockByTickerSymbol("GOOG"));
    }

    @Test
    void capitalizationDoesNotMatter(){
        assertDoesNotThrow(() -> stockService.getStockByTickerSymbol("gOoG"));
    }

    @Test
    void stockNotFoundThrowsError(){
        assertThrows(StockNotFoundException.class, () -> stockService.getStockByTickerSymbol("goin"));
    }

    @Test
    void allStocksAreSaved(){
        assertEquals(DefaultStocksList.getCountForDefaultStocks(), stockService.findStockRowCount());
        assertEquals(DefaultStocksList.getCountForDefaultStocks(), stockService.getAllStocks().size());
    }

    @Test
    void sortBySectorFiltersCorrect(){
        stockService.getAllStocksBySector("Technology").forEach(stock -> {
            assertEquals("Technology", stock.getSector());
        });
    }

    @Test
    void sortByMarketCapFiltersCorrect(){
        stockService.getAllStocksByMarketCap(MarketCap.Large).forEach(stock -> {
            assertEquals(MarketCap.Large, stock.getMarketCap());
        });
    }

    @Test
    void defaultStocksAreSorted(){
        List<Stock> fakeStocks = List.of(
                new Stock("S1", "Stock 1", "Test", MarketCap.Large, Volatility.NORMAL, InvestorRating.Neutral),
                new Stock("S2", "Stock 2", "Test", MarketCap.Large, Volatility.NORMAL, InvestorRating.Neutral),
                new Stock("S3", "Stock 3", "Test", MarketCap.Large, Volatility.NORMAL, InvestorRating.Neutral)
        );
        assertTrue(StockService.stockTickerExistsInList(fakeStocks, "S1"));
        assertTrue(StockService.stockTickerExistsInList(fakeStocks, "S2"));
        assertFalse(StockService.stockTickerExistsInList(fakeStocks, "S4"));
    }
}