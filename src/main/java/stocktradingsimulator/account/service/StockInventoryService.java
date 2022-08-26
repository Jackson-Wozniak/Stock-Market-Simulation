package stocktradingsimulator.account.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stocktradingsimulator.account.model.entity.Account;
import stocktradingsimulator.account.model.entity.StockInventory;
import stocktradingsimulator.account.exception.AccountNotFoundException;
import stocktradingsimulator.account.model.payload.BuyStock;
import stocktradingsimulator.account.repository.StockInventoryRepository;
import stocktradingsimulator.account.utils.FindStockInventory;

@Service
@AllArgsConstructor
public class StockInventoryService {

    @Autowired
    private final StockInventoryRepository stockOwnedRepository;
    @Autowired
    private final AccountService accountService;

    public void updateStockInventory(BuyStock buyStock) throws AccountNotFoundException {
        Account account =  accountService.getAccountByName(buyStock.getUsername());
        StockInventory stockInventory = FindStockInventory.findOwnedStockByTicker(
                account.getStocksOwned(), buyStock.getTicker());
        if(stockInventory == null) {
            saveNewStockOwned(buyStock, account);
            return;
        }
        stockInventory.setAmountOwned(stockInventory.getAmountOwned() + 1);
        stockOwnedRepository.save(stockInventory);
    }

    public void saveNewStockOwned(BuyStock buyStock, Account account){
        stockOwnedRepository.save(new StockInventory(account, buyStock.getTicker()));
    }
}
