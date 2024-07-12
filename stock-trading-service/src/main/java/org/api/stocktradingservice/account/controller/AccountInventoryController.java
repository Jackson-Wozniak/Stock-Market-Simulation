package org.api.stocktradingservice.account.controller;

import lombok.AllArgsConstructor;
import org.api.stocktradingservice.account.exception.AccountBalanceException;
import org.api.stocktradingservice.account.exception.AccountInventoryException;
import org.api.stocktradingservice.account.exception.AccountNotFoundException;
import org.api.stocktradingservice.account.model.entity.Account;
import org.api.stocktradingservice.account.model.entity.LimitOrder;
import org.api.stocktradingservice.account.model.payload.AccountCredentialsRequest;
import org.api.stocktradingservice.account.model.payload.LimitOrderRequest;
import org.api.stocktradingservice.account.model.payload.StockTransactionRequest;
import org.api.stocktradingservice.account.service.AccountService;
import org.api.stocktradingservice.account.service.AuthenticationService;
import org.api.stocktradingservice.account.service.LimitOrderService;
import org.api.stocktradingservice.account.service.StockOwnedService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/inventory")
@AllArgsConstructor
public class AccountInventoryController {

    private final StockOwnedService stockOwnedService;
    private final LimitOrderService limitOrderService;
    private final AuthenticationService authenticationService;

    @PostMapping(value = "/market")
    public void buyNewStock(@RequestBody StockTransactionRequest request) {
        stockOwnedService.buyStock(request);
    }

    @PostMapping
    public void sellStockInInventory(@RequestBody StockTransactionRequest request) {
        stockOwnedService.sellStock(request);
    }

    @PostMapping(value = "/limit")
    public ResponseEntity<List<LimitOrder>> limitOrder(@RequestBody LimitOrderRequest request) {
        Account account = authenticationService.authenticate(request.getUsername(), request.getPassword());
        limitOrderService.saveLimitOrder(new LimitOrder(
                account,
                request.getTicker(),
                request.getSharesToBuy(), request.getLimitPrice()));

        return ResponseEntity.ok(limitOrderService.findLimitOrdersByAccount(account));
    }

    @GetMapping(value = "/limit")
    public ResponseEntity<List<LimitOrder>> getLimitOrders(@RequestBody AccountCredentialsRequest request) {
        return ResponseEntity.ok(limitOrderService.findLimitOrdersByAccount(
                authenticationService.authenticate(request.getUsername(), request.getPassword())));
    }

}
