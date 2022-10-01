package org.api.stockmarket.stocks.stock.utils;

import org.api.stockmarket.stocks.stock.model.entity.StockHistory;
import org.api.tradinggame.account.model.entity.Account;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class SortStockHistory {

    private static final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    public static void sortStockHistoryByDate(List<StockHistory> stockHistory) {
        stockHistory.sort((history1, history2) -> {
            try {
                return dateFormat.parse(history1.getMarketDate())
                        .compareTo(dateFormat.parse(history2.getMarketDate()));
            } catch (ParseException e) {
                return 0;
            }
        });
    }
}
