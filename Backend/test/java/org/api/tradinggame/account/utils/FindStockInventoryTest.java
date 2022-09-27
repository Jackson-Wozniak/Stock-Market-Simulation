package org.api.tradinggame.account.utils;

import org.junit.jupiter.api.Test;
import org.api.tradinggame.account.model.entity.Account;
import org.api.tradinggame.account.model.entity.StockInventory;
import org.api.tradinggame.account.utils.FindStockInventory;

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