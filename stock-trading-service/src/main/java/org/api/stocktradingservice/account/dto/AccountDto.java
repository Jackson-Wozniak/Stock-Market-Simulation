package org.api.stocktradingservice.account.dto;

import lombok.Getter;
import lombok.Setter;
import org.api.stocktradingservice.account.model.entity.Account;
import org.api.stocktradingservice.account.model.entity.AccountHistory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class AccountDto {

    private String username;
    private double balance;
    private double totalProfits;
    private List<StockOwnedDto> stocksOwned;
    private Map<String, Double> accountBalanceHistory;

    public AccountDto(Account account){
        this.username = account.getUsername();
        this.balance = account.getAccountBalance();
        this.totalProfits = account.getTotalProfits();
        this.stocksOwned = account.getStocksOwned().stream().map(StockOwnedDto::new).toList();
        this.accountBalanceHistory = account.getAccountHistory().stream()
                .collect(Collectors.toMap(entry -> entry.getDate().toString(), AccountHistory::getBalance));
    }
}
