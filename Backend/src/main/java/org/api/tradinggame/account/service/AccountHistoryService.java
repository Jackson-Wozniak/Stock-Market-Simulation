package org.api.tradinggame.account.service;

import lombok.AllArgsConstructor;
import org.api.stockmarket.market.entity.Market;
import org.api.stockmarket.market.service.MarketService;
import org.api.stockmarket.stocks.stock.utils.SortHistory;
import org.api.tradinggame.account.model.entity.AccountHistory;
import org.api.tradinggame.account.repository.AccountHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountHistoryService {

    @Autowired
    private final AccountHistoryRepository accountHistoryRepository;
    @Autowired
    private final AccountService accountService;
    @Autowired
    private final MarketService marketService;

    public void saveDailyAccountHistory() {
        Market market = marketService.findMarketEntity();
        accountService.findAllAccounts().forEach(account -> accountHistoryRepository.save(
                new AccountHistory(market.getDate(), account, account.getTotalProfits()
                )));
    }

    public List<AccountHistory> findHistoryOfAccount(String username) {
        List<AccountHistory> accountHistory =
                accountService.getAccountByName(username).getAccountHistory();
        SortHistory.sortAccountHistoryByDate(accountHistory);
        return accountHistory;
    }

    @Transactional
    public void truncateAccountHistoryAtEndOfYear() {
        accountHistoryRepository.truncateTable();
    }
}
