package stocktradingsimulator.account.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stocktradingsimulator.account.entity.Account;
import stocktradingsimulator.account.entity.StockOwned;
import stocktradingsimulator.account.exception.AccountNotFoundException;
import stocktradingsimulator.account.payload.StockBuy;
import stocktradingsimulator.account.repository.StockOwnedRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StockOwnedService {

    @Autowired
    private StockOwnedRepository stockOwnedRepository;
    @Autowired
    private AccountService accountService;

    public void updateStockOwned(StockBuy stockBuy) throws AccountNotFoundException {
        Account account =  accountService.getAccountByName(stockBuy.getUsername());
        Set<StockOwned> stocksOwned = account.getStocksOwned();

        StockOwned stock = stocksOwned.stream()
                .findFirst()
                .filter(ticker -> ticker.getTicker().equals(stockBuy.getTicker()))
                .orElse(null);
        if(stock == null) {
            saveNewStockOwned(stockBuy, account);
            return;
        }
//        stocksOwned.remove(stock);
        stock.setAmountOwned(stock.getAmountOwned() + 1);
//        stocksOwned.add(stock);
//        account.setStocksOwned(stocksOwned);
//        accountService.saveAccount(account);
        stockOwnedRepository.save(stock);
    }

    public void saveNewStockOwned(StockBuy stockBuy, Account account){
        System.out.println("here");
        stockOwnedRepository.save(new StockOwned(account, stockBuy.getTicker()));
    }
}
