package org.api.tradinggame.account.controller;

import lombok.AllArgsConstructor;
import org.api.tradinggame.account.exception.AccountBalanceException;
import org.api.tradinggame.account.exception.AccountNotFoundException;
import org.api.tradinggame.account.exception.InvalidAccountException;
import org.api.tradinggame.account.model.entity.Account;
import org.api.tradinggame.account.model.payload.AccountTransaction;
import org.api.tradinggame.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/account")
@AllArgsConstructor
public class AccountController {

    @Autowired
    private final AccountService accountService;

    @RequestMapping(value = "get/{username}")
    public Account getAccountByUsername(@PathVariable String username) throws AccountNotFoundException {
        return accountService.getAccountByName(username);
    }

    @PostMapping(value = "create/{username}")
    public void createAccount(@PathVariable String username) throws InvalidAccountException {
        accountService.createNewAccount(username);
    }

    @PostMapping(value = "/deposit")
    public void depositToAccount(@RequestBody AccountTransaction accountTransaction) throws AccountNotFoundException, AccountBalanceException {
        accountService.updateAccountBalance(accountTransaction);
    }
}
