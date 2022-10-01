package org.api.stockmarket.stocks.stock.utils;

import org.api.stockmarket.stocks.stock.model.entity.Stock;
import org.api.stockmarket.stocks.stock.model.entity.StockHistory;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortStockHistoryTest {

    private static final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    private static final ArrayList<StockHistory> stockHistoryList1 = new ArrayList<>();

    @Test
    void stockHistorySorted() {
        populateStockHistoryList();
        SortStockHistory.sortStockHistoryByDate(stockHistoryList1);
        assertEquals("1/1/1", stockHistoryList1.get(0).getMarketDate());
        assertEquals("1/3/1", stockHistoryList1.get(1).getMarketDate());
        assertEquals("1/5/1", stockHistoryList1.get(2).getMarketDate());
        assertEquals("2/1/1", stockHistoryList1.get(3).getMarketDate());
    }

    private static void populateStockHistoryList() {
        stockHistoryList1.add(new StockHistory("1/1/1", "1", 0.0));
        stockHistoryList1.add(new StockHistory("1/5/1", "2", 0.0));
        stockHistoryList1.add(new StockHistory("2/1/1", "3", 0.0));
        stockHistoryList1.add(new StockHistory("1/3/1", "3", 0.0));
    }

}