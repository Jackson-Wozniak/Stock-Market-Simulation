package org.api.stocktradingservice.account.dto;

import lombok.Getter;
import lombok.Setter;
import org.api.stocktradingservice.account.model.entity.Account;

@Getter
@Setter
public class AccountInfoDto {

    private String username;
    private double balance;

    public AccountInfoDto(Account account){
        this.username = account.getUsername();
        this.balance = account.getAccountBalance();
    }
}
