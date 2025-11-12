package org.api.stockmarket.modules.stocks.csv;

import org.api.stockmarket.modules.stocks.entity.Stock;
import org.api.stockmarket.modules.stocks.factory.StockFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StockCSVReaderTest {
    private final StockFactory stockFactory = new StockFactory();
    private final StockCSVReader reader = new StockCSVReader(stockFactory);
    private final List<StockCSVObject> stockCSVObjects = List.of(
            new StockCSVObject(new String[] {"Test", "Test Company", "4", "Test", "Stable", "4", "1", "100"})
    );

    @Test
    void lineCountShouldMatchFile(){
        assertEquals(stockCSVObjects.size(), reader.lineCount());
    }

    @Test
    void readStocksShouldMatchFile(){
        List<StockCSVObject> readObjects = reader.map();
        assertEquals(stockCSVObjects.size(), readObjects.size());

        for(int i = 0; i < stockCSVObjects.size(); i++){
            StockCSVObject expectedStock = stockCSVObjects.get(i);
            StockCSVObject actualStock = readObjects.get(i);

            assertEquals(expectedStock.getTicker(), actualStock.getTicker());
            assertEquals(expectedStock.getCompanyName(), actualStock.getCompanyName());
            assertEquals(expectedStock.getMarketCap(), actualStock.getMarketCap());
            assertEquals(expectedStock.getSector(), actualStock.getSector());
            assertEquals(expectedStock.getVolatility(), actualStock.getVolatility());
            assertEquals(expectedStock.getInvestorRating(), actualStock.getInvestorRating());
            assertEquals(expectedStock.getInvestorStyle(), actualStock.getInvestorStyle());
            assertEquals(expectedStock.getInitialPrice(), actualStock.getInitialPrice());
        }
    }

    @Test
    void toEntityMapsCorrectly(){
        List<Stock> stocks = reader.toEntities();
        assertEquals(stockCSVObjects.size(), stocks.size());

        for(int i = 0; i < stockCSVObjects.size(); i++){
            Stock expectedStock = stockFactory.create(stockCSVObjects.get(i));
            Stock actualStock = stocks.get(i);

            assertEquals(expectedStock.getTicker(), actualStock.getTicker());
        }
    }
}