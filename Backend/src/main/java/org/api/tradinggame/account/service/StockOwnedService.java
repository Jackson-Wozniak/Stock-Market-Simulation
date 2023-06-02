package org.api.tradinggame.account.service;

import lombok.AllArgsConstructor;
import org.api.stockmarket.stocks.stock.entity.Stock;
import org.api.stockmarket.stocks.stock.service.StockService;
import org.api.tradinggame.account.exception.AccountBalanceException;
import org.api.tradinggame.account.exception.AccountInventoryException;
import org.api.tradinggame.account.model.entity.Account;
import org.api.tradinggame.account.model.entity.StockOwned;
import org.api.tradinggame.account.model.payload.BuyStockRequest;
import org.api.tradinggame.account.model.payload.SellStockRequest;
import org.api.tradinggame.account.repository.StockOwnedRepository;
import org.api.tradinggame.account.utils.ValidateStockTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StockOwnedService {

    @Autowired
    private final StockOwnedRepository stockOwnedRepository;
    @Autowired
    private final AccountService accountService;
    @Autowired
    private final StockService stockService;

    public void buyStock(BuyStockRequest buyStock) {
        Account account = accountService.getAccountByName(buyStock.getUsername());
        Stock stock = stockService.getStockByTickerSymbol(buyStock.getTicker());
        StockOwned stockOwned = findStockOwned(account, stock);
        if (!ValidateStockTransaction.doesAccountHaveEnoughMoney(account, buyStock, this.stockService)) {
            throw new AccountBalanceException("Account does not have funds for this purchase");
        }
        if (stockOwned != null) {
            //subtract transaction value from account balance
            accountService.updateBalanceAndSave(account, -1 * (buyStock.getSharesToBuy() * stock.getPrice()));
            stockOwned.updateCostBasisAndAmountOwned(buyStock.getSharesToBuy(), stock.getPrice());
            stockOwnedRepository.save(stockOwned);
            return;
        }
        accountService.updateBalanceAndSave(account, -1 * (buyStock.getSharesToBuy() * stock.getPrice()));
        saveNewStockOwned(buyStock, account, stock.getPrice());
    }

    public void saveNewStockOwned(BuyStockRequest buyStock, Account account, double stockPrice) {
        stockOwnedRepository.save(new StockOwned(
                account, buyStock.getTicker(), buyStock.getSharesToBuy(), stockPrice));
    }

    public void sellStock(SellStockRequest sellStock) {
        Account account = accountService.getAccountByName(sellStock.getUsername());
        if (!ValidateStockTransaction.doesAccountHaveEnoughStocks(account, sellStock)) {
            throw new AccountInventoryException("Account does not own enough stocks");
        }
        Stock stock = stockService.getStockByTickerSymbol(sellStock.getTicker());
        StockOwned stockOwned = findStockOwned(account, stock);
        account.updateTotalProfits(
                stockOwned.getCostBasis(),
                sellStock.getSharesToSell(),
                stock.getPrice());
        accountService.updateBalanceAndSave(account, stock.getPrice() * sellStock.getSharesToSell());
        if (sellStock.getSharesToSell() - stockOwned.getAmountOwned() == 0) {
            clearAndDeleteStockOwned(stockOwned);
        } else {
            stockOwned.setAmountOwned(stockOwned.getAmountOwned() - sellStock.getSharesToSell());
            stockOwnedRepository.save(stockOwned);
        }
    }

    public StockOwned findStockOwned(Account account, Stock stock) {
        return stockOwnedRepository.findAll().stream()
                .filter(stockOwned -> stockOwned.getTicker().equalsIgnoreCase(stock.getTicker()))
                .filter(stockOwned -> stockOwned.getAccount().getUsername().equals(account.getUsername()))
                .findFirst()
                .orElse(null);
    }

    public void clearAndDeleteStockOwned(StockOwned stockOwned) {
        stockOwnedRepository.delete(stockOwned);
    }
}
