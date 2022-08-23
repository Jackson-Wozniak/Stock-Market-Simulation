package stocktradingsimulator.account.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stocktradingsimulator.account.entity.Account;
import stocktradingsimulator.account.exception.AccountNotFoundException;
import stocktradingsimulator.account.payload.StockBuy;
import stocktradingsimulator.account.service.StockOwnedService;

@RestController
@RequestMapping(value = "/api/v1/inventory")
@AllArgsConstructor
public class AccountInventoryController {

    @Autowired
    private StockOwnedService stockOwnedService;

    @PostMapping(value = "/buy")
    public void buyNewStock(@RequestBody StockBuy stockBuy) throws AccountNotFoundException {
        stockOwnedService.updateStockOwned(stockBuy);
    }

}
