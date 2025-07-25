package org.api.stockmarket.modules.stocks.stock.utils;

import org.api.stockmarket.modules.stocks.entity.Stock;
import org.api.stockmarket.modules.stocks.enums.InvestorRating;
import org.api.stockmarket.modules.stocks.enums.MarketCap;
import org.api.stockmarket.modules.stocks.enums.Volatility;
import org.api.stockmarket.modules.stocks.properties.DefaultStockPrices;
import org.api.stockmarket.modules.stocks.utils.CsvReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvReaderTest {

    private static final int ROW_COUNT = 4;

    @Test
    void allStocksRead() throws IOException {
        CsvReader csvReader = new CsvReader("text/test_stocks.csv");

        List<Stock> stocks = csvReader.readAllStocks();

        assertEquals(ROW_COUNT, stocks.size());

        Stock test1 = stocks.get(0);
        assertEquals("test1", test1.getTicker());
        assertEquals("test1", test1.getCompanyName());
        assertEquals(MarketCap.Large, test1.getMarketCap());
        assertEquals("Technology", test1.getSector());
        assertEquals(Volatility.VOLATILE, test1.getVolatileStock());
        assertEquals(InvestorRating.Buy, test1.getInvestorRating());
        assertEquals(DefaultStockPrices.DEFAULT_LARGE_CAP_PRICE, test1.getPrice());

        Stock test2 = stocks.get(1);
        assertEquals("test2", test2.getTicker());
        assertEquals("test2", test2.getCompanyName());
        assertEquals(MarketCap.Large, test2.getMarketCap());
        assertEquals("Finance", test2.getSector());
        assertEquals(Volatility.STABLE, test2.getVolatileStock());
        assertEquals(InvestorRating.Neutral, test2.getInvestorRating());
        assertEquals(DefaultStockPrices.DEFAULT_LARGE_CAP_PRICE, test2.getPrice());

        Stock test3 = stocks.get(2);
        assertEquals("test3", test3.getTicker());
        assertEquals("test3", test3.getCompanyName());
        assertEquals(MarketCap.Small, test3.getMarketCap());
        assertEquals("Insurance", test3.getSector());
        assertEquals(Volatility.EXTRA_VOLATILE, test3.getVolatileStock());
        assertEquals(InvestorRating.Sell, test3.getInvestorRating());
        assertEquals(DefaultStockPrices.DEFAULT_SMALL_CAP_PRICE, test3.getPrice());

        Stock test4 = stocks.get(3);
        assertEquals("test4", test4.getTicker());
        assertEquals("test4", test4.getCompanyName());
        assertEquals(MarketCap.Mid, test4.getMarketCap());
        assertEquals("Healthcare", test4.getSector());
        assertEquals(Volatility.NORMAL, test4.getVolatileStock());
        assertEquals(InvestorRating.Hold, test4.getInvestorRating());
        assertEquals(DefaultStockPrices.DEFAULT_MID_CAP_PRICE, test4.getPrice());
    }
}