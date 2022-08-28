package stocktradingsimulator.account.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stocktradingsimulator.account.exception.AccountBalanceException;
import stocktradingsimulator.account.exception.AccountInventoryException;
import stocktradingsimulator.account.exception.AccountNotFoundException;
import stocktradingsimulator.account.model.payload.BuyStockRequest;
import stocktradingsimulator.account.model.payload.SellStockRequest;
import stocktradingsimulator.account.service.StockInventoryService;

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
