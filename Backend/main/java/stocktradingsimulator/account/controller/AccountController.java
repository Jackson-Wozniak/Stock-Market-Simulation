package stocktradingsimulator.account.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stocktradingsimulator.account.exception.AccountBalanceException;
import stocktradingsimulator.account.exception.AccountNotFoundException;
import stocktradingsimulator.account.exception.InvalidAccountException;
import stocktradingsimulator.account.model.entity.Account;
import stocktradingsimulator.account.model.payload.AccountTransaction;
import stocktradingsimulator.account.service.AccountService;

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
