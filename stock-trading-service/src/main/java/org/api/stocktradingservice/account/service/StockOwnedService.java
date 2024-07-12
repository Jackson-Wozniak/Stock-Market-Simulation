package org.api.stocktradingservice.account.service;

import lombok.AllArgsConstructor;
import org.api.stocktradingservice.account.client.StockMarketRestClient;
import org.api.stocktradingservice.account.client.StockResponse;
import org.api.stocktradingservice.account.model.payload.StockTransactionRequest;
import org.api.stocktradingservice.account.repository.StockOwnedRepository;
import org.api.stocktradingservice.account.exception.AccountBalanceException;
import org.api.stocktradingservice.account.exception.AccountInventoryException;
import org.api.stocktradingservice.account.model.entity.Account;
import org.api.stocktradingservice.account.model.entity.StockOwned;
import org.api.stocktradingservice.account.utils.ValidateStockTransaction;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StockOwnedService {

    private final StockOwnedRepository stockOwnedRepository;
    private final AccountService accountService;
    private final StockMarketRestClient stockMarketRestClient;

    public void buyStock(StockTransactionRequest request) {
        Account account = accountService.getAccountByName(request.getUsername());
        StockResponse stock = stockMarketRestClient.retrieveStockInfo(request.getTicker());
        StockOwned stockOwned = findStockOwned(account, stock);
        if (!ValidateStockTransaction.doesAccountHaveEnoughMoney(account, request, stock)) {
            throw new AccountBalanceException("Account does not have funds for this purchase");
        }
        if (stockOwned != null) {
            accountService.updateBalanceAndSave(account, -1 * (request.getCount() * stock.getPrice()));
            stockOwned.updateCostBasisAndAmountOwned(request.getCount(), stock.getPrice());
            stockOwnedRepository.save(stockOwned);
            return;
        }
        accountService.updateBalanceAndSave(account, -1 * (request.getCount() * stock.getPrice()));
        saveNewStockOwned(request, account, stock.getPrice());
    }

    public void saveNewStockOwned(StockTransactionRequest request, Account account, double stockPrice) {
        stockOwnedRepository.save(new StockOwned(
                account, request.getTicker(), request.getCount(), stockPrice));
    }

    public void sellStock(StockTransactionRequest request) {
        Account account = accountService.getAccountByName(request.getUsername());
        if (!ValidateStockTransaction.doesAccountHaveEnoughStocks(account, request)) {
            throw new AccountInventoryException("Account does not own enough stocks");
        }
        StockResponse stock = stockMarketRestClient.retrieveStockInfo(request.getTicker());
        StockOwned stockOwned = findStockOwned(account, stock);
        account.updateTotalProfits(
                stockOwned.getCostBasis(),
                request.getCount(),
                stock.getPrice());
        accountService.updateBalanceAndSave(account, stock.getPrice() * request.getCount());
        if (request.getCount() - stockOwned.getAmountOwned() == 0) {
            clearAndDeleteStockOwned(stockOwned);
        } else {
            stockOwned.setAmountOwned(stockOwned.getAmountOwned() - request.getCount());
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
