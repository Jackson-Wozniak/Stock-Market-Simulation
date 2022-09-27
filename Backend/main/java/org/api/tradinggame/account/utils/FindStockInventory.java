package org.api.tradinggame.account.utils;

import org.api.tradinggame.account.model.entity.StockInventory;

import java.util.Set;

/*
    Find the account owned stocks based on specific fields
 */
public class FindStockInventory {

    public static StockInventory findOwnedStockByTicker(Set<StockInventory> stocksOwned, String ticker){
        return stocksOwned.stream()
                .findFirst()
                .filter(stock -> stock.getTicker().equalsIgnoreCase(ticker))
                .orElse(null);
    }
}
