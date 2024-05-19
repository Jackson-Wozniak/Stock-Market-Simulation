package org.api.tradinggame.account.utils;

import org.api.stockmarket.stocks.stock.exception.StockNotFoundException;
import org.api.stockmarket.stocks.stock.entity.Stock;
import org.api.stockmarket.stocks.stock.service.StockService;
import org.api.tradinggame.account.model.entity.Account;
import org.api.tradinggame.account.model.entity.StockOwned;
import org.api.tradinggame.account.model.payload.BuyStockRequest;
import org.api.tradinggame.account.model.payload.SellStockRequest;

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
