package stocktradingsimulator.account.utils;

import org.junit.jupiter.api.Test;
import stocktradingsimulator.account.model.entity.Account;
import stocktradingsimulator.account.model.entity.StockInventory;
import stocktradingsimulator.stock.Stock;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FindStockInventoryTest {

    private static final Set<StockInventory> stocksOwned = Set.of(
            new StockInventory(new Account("test"), "AMZN"));
    private static final Set<StockInventory> emptyStocksOwned = Set.of();

    @Test
    void findStockTicker(){
        StockInventory stock = FindStockInventory.findOwnedStockByTicker(stocksOwned, "AMZN");
        assertEquals(stock.getTicker(), "AMZN");
    }

    @Test
    void cannotFindStockReturnsNull(){
        assertNull(FindStockInventory.findOwnedStockByTicker(emptyStocksOwned, "AMZN"));
    }

}