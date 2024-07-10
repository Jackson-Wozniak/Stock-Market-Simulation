package org.api.stocktradingservice.account.service;

import lombok.AllArgsConstructor;
import org.api.stocktradingservice.account.client.StockMarketRestClient;
import org.api.stocktradingservice.account.client.StockResponse;
import org.api.stocktradingservice.account.repository.StockOwnedRepository;
import org.api.stocktradingservice.account.exception.AccountBalanceException;
import org.api.stocktradingservice.account.exception.AccountInventoryException;
import org.api.stocktradingservice.account.model.entity.Account;
import org.api.stocktradingservice.account.model.entity.StockOwned;
import org.api.stocktradingservice.account.model.payload.BuyStockRequest;
import org.api.stocktradingservice.account.model.payload.SellStockRequest;
import org.api.stocktradingservice.account.utils.ValidateStockTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StockOwnedService {

    private final StockOwnedRepository stockOwnedRepository;
    private final AccountService accountService;
    private final StockMarketRestClient stockMarketRestClient;

    public void buyStock(BuyStockRequest buyStock) {
        Account account = accountService.getAccountByName(buyStock.getUsername());
        StockResponse stock = stockMarketRestClient.retrieveStockInfo(buyStock.getTicker());
        StockOwned stockOwned = findStockOwned(account, stock);
        if (!ValidateStockTransaction.doesAccountHaveEnoughMoney(account, buyStock, stock)) {
            throw new AccountBalanceException("Account does not have funds for this purchase");
        }
        if (stockOwned != null) {
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
        StockResponse stock = stockMarketRestClient.retrieveStockInfo(sellStock.getTicker());
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

    public StockOwned findStockOwned(Account account, StockResponse stock) {
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
