package org.api.stockmarket.market.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/market/sim")
@AllArgsConstructor
public class MarketSimulationController {

    /*
    TODO: simulate()
        - get all stocks with their current price
        - simulate their price fluctuations by getting the priceChangePerDay * inputDays
        - return their price history
        - do not save the stocks, instead simulating only in memory
     */
}
