package org.api.stocktradingservice.account.controller;

import lombok.AllArgsConstructor;
import org.api.stocktradingservice.account.client.StockMarketRestClient;
import org.api.stocktradingservice.account.exception.AccountBalanceException;
import org.api.stocktradingservice.account.exception.AccountInventoryException;
import org.api.stocktradingservice.account.exception.AccountNotFoundException;
import org.api.stocktradingservice.account.model.entity.LimitOrder;
import org.api.stocktradingservice.account.model.payload.BuyStockRequest;
import org.api.stocktradingservice.account.model.payload.LimitOrderRequest;
import org.api.stocktradingservice.account.model.payload.SellStockRequest;
import org.api.stocktradingservice.account.service.AccountService;
import org.api.stocktradingservice.account.service.LimitOrderService;
import org.api.stocktradingservice.account.service.StockOwnedService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/inventory")
@AllArgsConstructor
public class AccountInventoryController {

    private final StockOwnedService stockOwnedService;
    private final LimitOrderService limitOrderService;
    private final AccountService accountService;
    private final StockMarketRestClient stockMarketRestClient;

    @PostMapping(value = "/buy/market")
    public void buyNewStock(@RequestBody BuyStockRequest buyStock)
            throws AccountNotFoundException, AccountBalanceException {
        stockOwnedService.buyStock(buyStock);
    }

    @PostMapping(value = "/sell")
    public void sellStockInInventory(@RequestBody SellStockRequest sellStock)
            throws AccountNotFoundException, AccountBalanceException, AccountInventoryException {
        stockOwnedService.sellStock(sellStock);
    }

    @PostMapping(value = "/buy/limit")
    public List<LimitOrder> limitOrder(@RequestBody LimitOrderRequest request) {
        limitOrderService.saveLimitOrder(new LimitOrder(
                accountService.getAccountByName(request.getUsername()),
                request.getTicker(),
                request.getSharesToBuy(), request.getLimitPrice()));

        return limitOrderService.findLimitOrdersByAccount(
                accountService.getAccountByName("default"));
    }

    @RequestMapping(value = "/orders/get/{username}")
    public List<LimitOrder> getAllLimitOrdersByUsername(@PathVariable String username) {
        return limitOrderService.findLimitOrdersByAccount(accountService.getAccountByName(username));
    }

}
