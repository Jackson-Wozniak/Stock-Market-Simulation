package org.api.tradinggame.account.controller;

import lombok.AllArgsConstructor;
import org.api.stockmarket.stocks.stock.service.StockService;
import org.api.tradinggame.account.exception.AccountBalanceException;
import org.api.tradinggame.account.exception.AccountInventoryException;
import org.api.tradinggame.account.exception.AccountNotFoundException;
import org.api.tradinggame.account.model.entity.LimitOrder;
import org.api.tradinggame.account.model.payload.BuyStockRequest;
import org.api.tradinggame.account.model.payload.LimitOrderRequest;
import org.api.tradinggame.account.model.payload.SellStockRequest;
import org.api.tradinggame.account.service.AccountService;
import org.api.tradinggame.account.service.LimitOrderService;
import org.api.tradinggame.account.service.StockOwnedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/inventory")
@AllArgsConstructor
public class AccountInventoryController {

    @Autowired
    private final StockOwnedService stockOwnedService;
    @Autowired
    private final LimitOrderService limitOrderService;
    @Autowired
    private final AccountService accountService;
    @Autowired
    private final StockService stockService;

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
                stockService.getStockByTickerSymbol(request.getTicker()),
                request.getSharesToBuy(), request.getLimitPrice()));

        return limitOrderService.findLimitOrdersByAccount(
                accountService.getAccountByName("default"));
    }

    @RequestMapping(value = "/orders/get/{username}")
    public List<LimitOrder> getAllLimitOrdersByUsername(@PathVariable String username) {
        return limitOrderService.findLimitOrdersByAccount(accountService.getAccountByName(username));
    }

}
