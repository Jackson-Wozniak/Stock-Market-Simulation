package org.api.tradinggame.account.controller;

import lombok.AllArgsConstructor;
import org.api.tradinggame.account.model.payload.BuyStockRequest;
import org.api.tradinggame.account.model.payload.SellStockRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.api.tradinggame.account.exception.AccountBalanceException;
import org.api.tradinggame.account.exception.AccountInventoryException;
import org.api.tradinggame.account.exception.AccountNotFoundException;
import org.api.tradinggame.account.service.StockInventoryService;

@RestController
@RequestMapping(value = "/api/v1/inventory")
@AllArgsConstructor
public class AccountInventoryController {

    @Autowired
    private final StockInventoryService stockInventoryService;

    @PostMapping(value = "/buy")
    public void buyNewStock(@RequestBody BuyStockRequest buyStock)
            throws AccountNotFoundException, AccountBalanceException {
        stockInventoryService.buyStock(buyStock);
    }

    @PostMapping(value = "/sell")
    public void sellStockInInventory(@RequestBody SellStockRequest sellStock)
            throws AccountNotFoundException, AccountBalanceException, AccountInventoryException {
        stockInventoryService.sellStock(sellStock);
    }

}
