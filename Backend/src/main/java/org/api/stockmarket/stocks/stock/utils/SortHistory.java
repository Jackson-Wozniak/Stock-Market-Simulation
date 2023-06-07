package org.api.stockmarket.stocks.stock.utils;

import org.api.stockmarket.stocks.stock.entity.StockPriceHistory;
import org.api.tradinggame.account.model.entity.AccountHistory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class SortHistory {

    private static final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    public static void sortStockHistoryByDate(List<StockPriceHistory> stockPriceHistory) {
        stockPriceHistory.sort((history1, history2) -> {
            try {
                return dateFormat.parse(history1.getId().getMarketDate())
                        .compareTo(dateFormat.parse(history2.getId().getMarketDate()));
            } catch (ParseException e) {
                return 0;
            }
        });
    }

    public static void sortAccountHistoryByDate(List<AccountHistory> accountHistory) {
        accountHistory.sort((history1, history2) -> {
            try {
                return dateFormat.parse(history1.getDate())
                        .compareTo(dateFormat.parse(history2.getDate()));
            } catch (ParseException e) {
                return 0;
            }
        });
    }
}
