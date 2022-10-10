package org.api.stockmarket.stocks.stock.model.payload;

import org.api.stockmarket.stocks.stock.enums.MarketCap;
import org.api.stockmarket.stocks.stock.model.entity.Stock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockDtoTest {

    @Test
    void percentChangePositive() {
        //create stock and stock DTO with price values
        Stock stock = new Stock();
        stock.setPrice(100.0);
        stock.setLastDayPrice(50.0);
        StockDto stockDto = new StockDto(stock, null);
        assertEquals(100, stockDto.getPercentChange());

        //create stock and stock DTO with price values
        stock.setPrice(128.11);
        stock.setLastDayPrice(13.243);
        stockDto = new StockDto(stock, null);
        assertEquals(867.38, stockDto.getPercentChange());
    }

    @Test
    void percentChangeNegative() {
        //create stock and stock DTO with price values
        Stock stock = new Stock();
        stock.setPrice(50.0);
        stock.setLastDayPrice(100.0);
        StockDto stockDto = new StockDto(stock, null);
        assertEquals(-50, stockDto.getPercentChange());

        //create stock and stock DTO with price values
        stock.setPrice(26.39);
        stock.setLastDayPrice(88.45);
        stockDto = new StockDto(stock, null);
        assertEquals(-70.16, stockDto.getPercentChange());
    }

    @Test
    void doesNotThrowArithmeticError() {
        //create stock and stock DTO with price values
        Stock stock = new Stock();
        stock.setPrice(0.0);
        stock.setLastDayPrice(0.0);
        StockDto stockDto = new StockDto(stock, null);
        assertDoesNotThrow(() -> stockDto.getPercentChange(0, 0));
    }

}