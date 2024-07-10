package org.api.stocktradingservice.account.utils;

import org.api.stocktradingservice.account.client.StockResponse;
import org.api.stocktradingservice.account.model.payload.BuyStockRequest;
import org.api.stocktradingservice.account.model.payload.SellStockRequest;
import org.api.stocktradingservice.account.model.entity.Account;
import org.api.stocktradingservice.account.model.entity.StockOwned;

public class ValidateStockTransaction {


    public static boolean doesAccountHaveEnoughMoney(Account account,
                                                     BuyStockRequest buyStockRequest,
                                                     StockResponse stockResponse) {
        double balance = account.getAccountBalance();
        return balance > (stockResponse.getPrice() * buyStockRequest.getSharesToBuy());
    }

    public static boolean doesAccountHaveEnoughStocks(Account account,
                                                      SellStockRequest sellStock) {
        StockOwned stock = FindStockOwned.findOwnedStockByTicker(
                account.getStocksOwned(), sellStock.getTicker());
        if (stock == null) return false;
        return stock.getAmountOwned() >= sellStock.getSharesToSell();
    }
}
