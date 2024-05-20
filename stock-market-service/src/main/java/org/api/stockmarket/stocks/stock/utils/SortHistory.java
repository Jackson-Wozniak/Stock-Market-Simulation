package org.api.stockmarket.stocks.stock.utils;

import java.util.List;

import org.api.stockmarket.stocks.stock.entity.StockPriceHistory;

public class SortHistory {

    public static void sortStockHistoryByDate(List<StockPriceHistory> stockPriceHistory) {
        stockPriceHistory.sort((history1, history2) -> {
            return history1.getId().getMarketDate().compareTo(history2.getId().getMarketDate());
        });
    }
}
