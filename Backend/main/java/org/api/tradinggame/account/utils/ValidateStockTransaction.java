package org.api.tradinggame.account.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.api.stockmarket.stocks.stock.exception.StockNotFoundException;
import org.api.stockmarket.stocks.stock.model.entity.Stock;
import org.api.stockmarket.stocks.stock.service.StockService;
import org.api.tradinggame.account.model.entity.Account;
import org.api.tradinggame.account.model.entity.StockInventory;
import org.api.tradinggame.account.model.payload.BuyStockRequest;
import org.api.tradinggame.account.model.payload.SellStockRequest;

@Component
@AllArgsConstructor
public class ValidateStockTransaction {


    public static boolean doesAccountHaveEnoughMoney(Account account,
                                                     BuyStockRequest buyStockRequest,
                                                     StockService stockService){
        double balance = account.getAccountBalance();
        Stock stock;
        try{
            stock =  stockService.getStockByTickerSymbol(buyStockRequest.getTicker());
        }catch (StockNotFoundException ex){
            return false;
        }
        return balance > (stock.getPrice() * buyStockRequest.getAmountToBuy());
    }

    public static boolean doesAccountHaveEnoughStocks(Account account,
                                                     SellStockRequest sellStock){
        StockInventory stock = FindStockInventory.findOwnedStockByTicker(
                account.getStocksOwned(), sellStock.getTicker());
        return stock.getAmountOwned() >= sellStock.getAmountToSell();
    }
}
