package org.api.stocktradingservice.account.utils;

import org.api.stocktradingservice.account.model.entity.StockOwned;

import java.util.Set;

/*
    Find the account owned stocks based on specific fields
 */
public class FindStockOwned {

    public static StockOwned findOwnedStockByTicker(Set<StockOwned> stocksOwned, String ticker) {
        return stocksOwned.stream()
                .findFirst()
                .filter(stock -> stock.getTicker().equalsIgnoreCase(ticker))
                .orElse(null);
    }
}
