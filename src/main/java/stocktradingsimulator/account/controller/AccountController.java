package stocktradingsimulator.account.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stocktradingsimulator.account.model.entity.Account;
import stocktradingsimulator.account.exception.AccountNotFoundException;
import stocktradingsimulator.account.exception.InvalidAccountException;
import stocktradingsimulator.account.service.AccountService;

@RestController
@RequestMapping(value = "/api/v1/account")
@AllArgsConstructor
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "get/{username}")
    public Account getAccountByUsername(@PathVariable String username) throws AccountNotFoundException {
        return accountService.getAccountByName(username);
    }

    @PostMapping(value = "create/{username}")
    public void createAccount(@PathVariable String username) throws InvalidAccountException {
        accountService.createNewAccount(username);
    }
}
