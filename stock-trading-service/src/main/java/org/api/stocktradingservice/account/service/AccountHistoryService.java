package org.api.stocktradingservice.account.service;

import lombok.AllArgsConstructor;
import org.api.stocktradingservice.account.client.MarketResponse;
import org.api.stocktradingservice.account.client.StockMarketRestClient;
import org.api.stocktradingservice.account.repository.AccountHistoryRepository;
import org.api.stocktradingservice.account.model.entity.AccountHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountHistoryService {

    @Autowired
    private final AccountHistoryRepository accountHistoryRepository;
    @Autowired
    private final AccountService accountService;
    @Autowired
    private final StockMarketRestClient stockMarketRestClient;

    public void saveDailyAccountHistory() {
        MarketResponse market = stockMarketRestClient.retrieveMarketInfo();
//        accountService.findAllAccounts().forEach(account -> accountHistoryRepository.save(
//                new AccountHistory(market.getDate(), account)));
        accountService.findAllAccounts().forEach(account -> accountHistoryRepository.save(
                new AccountHistory(ZonedDateTime.now(), account)));
    }

    public List<AccountHistory> findHistoryOfAccount(String username) {
        List<AccountHistory> accountHistory =
                accountService.getAccountByName(username).getAccountHistory();
        //SortHistory.sortAccountHistoryByDate(accountHistory);
        return accountHistory;
    }

    @Transactional
    public void truncateAccountHistoryAtEndOfYear() {
        accountHistoryRepository.truncateTable();
    }
}
