package org.api.stockmarket.stocks.stock.utils;

import org.api.stockmarket.stocks.stock.entity.StockPriceHistory;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SortHistoryTest {

    private static final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    private static final ArrayList<StockPriceHistory> STOCK_PRICE_HISTORY_LIST_1 = new ArrayList<>();

    @Test
    void stockHistorySorted() {
        populateStockHistoryList();
        SortHistory.sortStockHistoryByDate(STOCK_PRICE_HISTORY_LIST_1);
        assertEquals("1/1/1", STOCK_PRICE_HISTORY_LIST_1.get(0).getMarketDate());
        assertEquals("1/3/1", STOCK_PRICE_HISTORY_LIST_1.get(1).getMarketDate());
        assertEquals("1/5/1", STOCK_PRICE_HISTORY_LIST_1.get(2).getMarketDate());
        assertEquals("2/1/1", STOCK_PRICE_HISTORY_LIST_1.get(3).getMarketDate());
    }

    private static void populateStockHistoryList() {
        STOCK_PRICE_HISTORY_LIST_1.add(new StockPriceHistory("1/1/1", "1", 0.0));
        STOCK_PRICE_HISTORY_LIST_1.add(new StockPriceHistory("1/5/1", "2", 0.0));
        STOCK_PRICE_HISTORY_LIST_1.add(new StockPriceHistory("2/1/1", "3", 0.0));
        STOCK_PRICE_HISTORY_LIST_1.add(new StockPriceHistory("1/3/1", "3", 0.0));
    }

}