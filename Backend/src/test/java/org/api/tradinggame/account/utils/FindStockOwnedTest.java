package org.api.tradinggame.account.utils;

import org.junit.jupiter.api.Test;
import org.api.tradinggame.account.model.entity.Account;
import org.api.tradinggame.account.model.entity.StockOwned;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FindStockOwnedTest {

    private static final Set<StockOwned> stocksOwned = Set.of(
            new StockOwned(new Account("test"), "AMZN", 1, 0.0));
    private static final Set<StockOwned> emptyStocksOwned = Set.of();

    @Test
    void findStockTicker() {
        StockOwned stock = FindStockOwned.findOwnedStockByTicker(stocksOwned, "AMZN");
        assertEquals(stock.getTicker(), "AMZN");
    }

    @Test
    void cannotFindStockReturnsNull() {
        assertNull(FindStockOwned.findOwnedStockByTicker(emptyStocksOwned, "AMZN"));
    }

}