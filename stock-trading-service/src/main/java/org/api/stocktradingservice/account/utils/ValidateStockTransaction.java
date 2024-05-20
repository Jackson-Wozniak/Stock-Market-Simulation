package org.api.stocktradingservice.account.utils;

import org.api.stockmarket.stocks.stock.exception.StockNotFoundException;
import org.api.stockmarket.stocks.stock.entity.Stock;
import org.api.stockmarket.stocks.stock.service.StockService;
import org.api.stocktradingservice.account.model.payload.BuyStockRequest;
import org.api.stocktradingservice.account.model.payload.SellStockRequest;
import org.api.stocktradingservice.account.model.entity.Account;
import org.api.stocktradingservice.account.model.entity.StockOwned;

public class ValidateStockTransaction {


    public static boolean doesAccountHaveEnoughMoney(Account account,
                                                     BuyStockRequest buyStockRequest,
                                                     StockService stockService) {
        double balance = account.getAccountBalance();
        Stock stock;
        try {
            stock = stockService.getStockByTickerSymbol(buyStockRequest.getTicker());
        } catch (StockNotFoundException ex) {
            return false;
        }
        return balance > (stock.getPrice() * buyStockRequest.getSharesToBuy());
    }

    public static boolean doesAccountHaveEnoughStocks(Account account,
                                                      SellStockRequest sellStock) {
        StockOwned stock = FindStockOwned.findOwnedStockByTicker(
                account.getStocksOwned(), sellStock.getTicker());
        if (stock == null) return false;
        return stock.getAmountOwned() >= sellStock.getSharesToSell();
    }
}
