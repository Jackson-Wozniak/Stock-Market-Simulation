package org.api.stocktradingservice.account.controller;

import lombok.AllArgsConstructor;
import org.api.stocktradingservice.account.exception.InvalidAccountException;
import org.api.stocktradingservice.account.model.payload.AccountCredentialsRequest;
import org.api.stocktradingservice.account.model.payload.DepositRequest;
import org.api.stocktradingservice.account.service.AccountHistoryService;
import org.api.stocktradingservice.account.service.AccountService;
import org.api.stocktradingservice.account.exception.AccountBalanceException;
import org.api.stocktradingservice.account.exception.AccountNotFoundException;
import org.api.stocktradingservice.account.model.entity.Account;
import org.api.stocktradingservice.account.model.entity.AccountHistory;
import org.api.stocktradingservice.account.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/account")
@AllArgsConstructor
public class AccountController {

    private final AuthenticationService authenticationService;

    @RequestMapping
    public ResponseEntity<Account> getAccountByUsername(@RequestBody AccountCredentialsRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request.getUsername(), request.getPassword()));
    }

    @PostMapping
    public void createAccount(@RequestBody AccountCredentialsRequest request) {
        authenticationService.createAccount(request.getUsername(), request.getPassword());
    }

    @PostMapping(value = "/deposit")
    public void depositToAccount(@RequestBody DepositRequest request) {
        authenticationService.updateBalance(request);
    }

    @GetMapping(value = "/history")
    public ResponseEntity<List<AccountHistory>> getAccountHistory(@RequestBody AccountCredentialsRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(
                request.getUsername(), request.getPassword()).getAccountHistory());
    }
}
