package stocktradingsimulator.account.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stocktradingsimulator.account.exception.AccountNotFoundException;
import stocktradingsimulator.account.model.payload.BuyStock;
import stocktradingsimulator.account.service.StockInventoryService;

@RestController
@RequestMapping(value = "/api/v1/inventory")
@AllArgsConstructor
public class AccountInventoryController {

    @Autowired
    private final StockInventoryService stockInventoryService;

    @PostMapping(value = "/buy")
    public void buyNewStock(@RequestBody BuyStock buyStock) throws AccountNotFoundException {
        stockInventoryService.updateStockInventory(buyStock);
    }

}
