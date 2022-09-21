package stocktradingsimulator.account.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stocktradingsimulator.account.exception.AccountBalanceException;
import stocktradingsimulator.account.exception.AccountInventoryException;
import stocktradingsimulator.account.exception.AccountNotFoundException;
import stocktradingsimulator.account.model.entity.Account;
import stocktradingsimulator.account.model.entity.StockInventory;
import stocktradingsimulator.account.model.payload.BuyStockRequest;
import stocktradingsimulator.account.model.payload.SellStockRequest;
import stocktradingsimulator.account.repository.StockInventoryRepository;
import stocktradingsimulator.account.utils.FindStockInventory;
import stocktradingsimulator.account.utils.ValidateStockTransaction;
import stocktradingsimulator.stock.StockService;

@Service
@AllArgsConstructor
public class StockInventoryService {

    @Autowired
    private final StockInventoryRepository stockOwnedRepository;
    @Autowired
    private final AccountService accountService;
    @Autowired
    private final StockService stockService;

    public void buyStock(BuyStockRequest buyStock) throws AccountNotFoundException, AccountBalanceException {
        Account account =  accountService.getAccountByName(buyStock.getUsername());
        StockInventory stockInventory = FindStockInventory.findOwnedStockByTicker(
                account.getStocksOwned(), buyStock.getTicker());
        if(!ValidateStockTransaction.doesAccountHaveEnoughMoney(account, buyStock, this.stockService)){
            throw new AccountBalanceException("Account does not have funds for this purchase");
        }
        if(stockInventory == null) {
            saveNewStockOwned(buyStock, account);
            return;
        }
        stockInventory.setAmountOwned(stockInventory.getAmountOwned() + buyStock.getAmountToBuy());
        stockOwnedRepository.save(stockInventory);
    }

    public void saveNewStockOwned(BuyStockRequest buyStock, Account account){
        stockOwnedRepository.save(new StockInventory(account, buyStock.getTicker()));
    }

    public void saveNewStockOwned(SellStockRequest sellStock, Account account){
        stockOwnedRepository.save(new StockInventory(account, sellStock.getTicker()));
    }

    public void sellStock(SellStockRequest sellStock) throws AccountNotFoundException, AccountInventoryException {
        Account account = accountService.getAccountByName(sellStock.getUsername());
        StockInventory stockInventory = FindStockInventory.findOwnedStockByTicker(
                account.getStocksOwned(), sellStock.getTicker());

        if(!ValidateStockTransaction.doesAccountHaveEnoughStocks(account, sellStock)){
            throw new AccountInventoryException("Account does not own enough stocks");
        }

        stockInventory.setAmountOwned(stockInventory.getAmountOwned() - sellStock.getAmountToSell());
        saveNewStockOwned(sellStock, account);
    }
}
