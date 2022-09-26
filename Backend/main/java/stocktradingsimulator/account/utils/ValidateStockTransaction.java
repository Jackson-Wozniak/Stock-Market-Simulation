package stocktradingsimulator.account.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import stocktradingsimulator.account.model.entity.Account;
import stocktradingsimulator.account.model.entity.StockInventory;
import stocktradingsimulator.account.model.payload.BuyStockRequest;
import stocktradingsimulator.account.model.payload.SellStockRequest;
import stocktradingsimulator.stocks.stock.model.entity.Stock;
import stocktradingsimulator.stocks.stock.exception.StockNotFoundException;
import stocktradingsimulator.stocks.stock.service.StockService;

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
