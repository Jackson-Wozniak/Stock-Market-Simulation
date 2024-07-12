package org.api.stocktradingservice.account.utils;

import org.api.stocktradingservice.account.client.StockResponse;
import org.api.stocktradingservice.account.model.entity.Account;
import org.api.stocktradingservice.account.model.entity.StockOwned;
import org.api.stocktradingservice.account.model.payload.StockTransactionRequest;

public class ValidateStockTransaction {


    public static boolean doesAccountHaveEnoughMoney(Account account,
                                                     StockTransactionRequest buyStockRequest,
                                                     StockResponse stockResponse) {
        double balance = account.getAccountBalance();
        return balance > (stockResponse.getPrice() * buyStockRequest.getCount());
    }

    public static boolean doesAccountHaveEnoughStocks(Account account,
                                                      StockTransactionRequest sellStock) {
        StockOwned stock = FindStockOwned.findOwnedStockByTicker(
                account.getStocksOwned(), sellStock.getTicker());
        if (stock == null) return false;
        return stock.getAmountOwned() >= sellStock.getCount();
    }
}
