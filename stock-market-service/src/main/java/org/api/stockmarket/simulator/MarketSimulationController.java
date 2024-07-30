package org.api.stockmarket.simulator;

import lombok.AllArgsConstructor;
import org.api.stockmarket.stocks.stock.dto.StockPriceHistoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/market/sim")
@AllArgsConstructor
public class MarketSimulationController {

    private MarketSimulationManager marketSimulationManager;

    private static final int DEFAULT_STOCK_COUNT = 20;
    private static final int DEFAULT_DAYS_SIMULATED = 30;

    /*
    TODO: simulate()
        - get all stocks with their current price
        - simulate their price fluctuations by getting the priceChangePerDay * inputDays
        - return their price history
        - do not save the stocks, instead simulating only in memory
     */

    //Only prices are relevant, meaning this simulation can use default stocks instead of real ones
    @GetMapping(value = "/price_history")
    public ResponseEntity<List<SimulatedStockDTO>> simulatePrices(
            @RequestParam(name = "days") Optional<Integer> daysSimulatedParam,
            @RequestParam(name = "stocks") Optional<Integer> stockCountParam){
        int daysSimulated = daysSimulatedParam.orElse(DEFAULT_DAYS_SIMULATED);
        int stockCount = stockCountParam.orElse(DEFAULT_STOCK_COUNT);

        return ResponseEntity.ok(marketSimulationManager.simulate(daysSimulated, stockCount)
                .stream().map(SimulatedStockDTO::new).toList());
    }
}
