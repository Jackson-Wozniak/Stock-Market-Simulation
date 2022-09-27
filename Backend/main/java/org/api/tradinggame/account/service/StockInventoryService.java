package org.api.tradinggame.account.service;

import lombok.AllArgsConstructor;
import org.api.stockmarket.stocks.stock.model.entity.Stock;
import org.api.stockmarket.stocks.stock.service.StockService;
import org.api.tradinggame.account.exception.AccountBalanceException;
import org.api.tradinggame.account.exception.AccountInventoryException;
import org.api.tradinggame.account.exception.AccountNotFoundException;
import org.api.tradinggame.account.model.entity.Account;
import org.api.tradinggame.account.model.entity.StockInventory;
import org.api.tradinggame.account.model.payload.AccountTransaction;
import org.api.tradinggame.account.model.payload.BuyStockRequest;
import org.api.tradinggame.account.model.payload.SellStockRequest;
import org.api.tradinggame.account.repository.StockInventoryRepository;
import org.api.tradinggame.account.utils.ValidateStockTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StockInventoryService {

    @Autowired
    private final StockInventoryRepository stockOwnedRepository;
    @Autowired
    private final AccountService accountService;
    @Autowired
    private final StockService stockService;

    public void buyStock(BuyStockRequest buyStock){
        Account account =  accountService.getAccountByName(buyStock.getUsername());
        Stock stock = stockService.getStockByTickerSymbol(buyStock.getTicker());
        StockInventory stockInventory = findStockInventory(
                account, stock);

        if(!ValidateStockTransaction.doesAccountHaveEnoughMoney(account, buyStock, this.stockService)){
            throw new AccountBalanceException("Account does not have funds for this purchase");
        }
        if(stockInventory == null) {
            saveNewStockOwned(buyStock, account);
        }else{
            //subtract transaction value from account balance
            accountService.updateAccountBalance(new AccountTransaction(account.getUsername(),
                    (stock.getPrice() * buyStock.getSharesToBuy()) * -1
            ));
            stockInventory.setAmountOwned(stockInventory.getAmountOwned() + buyStock.getSharesToBuy());
            stockOwnedRepository.save(stockInventory);
        }
    }

    public void saveNewStockOwned(BuyStockRequest buyStock, Account account){
        stockOwnedRepository.save(new StockInventory(account, buyStock.getTicker()));
    }

    public void saveNewStockOwned(SellStockRequest sellStock, Account account){
        stockOwnedRepository.save(new StockInventory(account, sellStock.getTicker()));
    }

    public void sellStock(SellStockRequest sellStock) throws AccountNotFoundException, AccountInventoryException {
        Account account = accountService.getAccountByName(sellStock.getUsername());
        Stock stock = stockService.getStockByTickerSymbol(sellStock.getTicker().toUpperCase());
        StockInventory stockInventory = findStockInventory(
                account, stock);

        if(!ValidateStockTransaction.doesAccountHaveEnoughStocks(account, sellStock)){
            throw new AccountInventoryException("Account does not own enough stocks");
        }

        accountService.updateAccountBalance(new AccountTransaction(account.getUsername(),
                stock.getPrice() * sellStock.getSharesToSell()
        ));

        stockInventory.setAmountOwned(stockInventory.getAmountOwned() - sellStock.getSharesToSell());
        stockOwnedRepository.save(stockInventory);
    }

    public StockInventory findStockInventory(Account account, Stock stock){
        return stockOwnedRepository.findAll().stream()
                .filter(stockOwned -> stockOwned.getTicker().equalsIgnoreCase(stock.getTicker()))
                .filter(stockOwned -> stockOwned.getAccount().getUsername().equals(account.getUsername()))
                .findFirst()
                .orElse(null);
    }
}
