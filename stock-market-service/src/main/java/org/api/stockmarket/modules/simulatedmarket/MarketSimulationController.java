package org.api.stockmarket.modules.simulatedmarket;

import lombok.AllArgsConstructor;
import org.api.stockmarket.modules.stocks.entity.PricingModel;
import org.api.stockmarket.modules.stocks.entity.Stock;
import org.api.stockmarket.modules.stocks.enums.InvestorRating;
import org.api.stockmarket.modules.stocks.enums.PriceVolatility;
import org.api.stockmarket.modules.stocks.enums.Volatility;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/market/sim")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class MarketSimulationController {

//    private MarketSimulationManager marketSimulationManager;
//
//    private static final int DEFAULT_STOCK_COUNT = 20;
//    private static final int DEFAULT_DAYS_SIMULATED = 30;
//
//    /*
//    TODO: simulate()
//        - get all stocks with their current price
//        - simulate their price fluctuations by getting the priceChangePerDay * inputDays
//        - return their price history
//        - do not save the stocks, instead simulating only in memory
//     */
//
//    //Only prices are relevant, meaning this simulation can use default stocks instead of real ones
//    @GetMapping
//    public ResponseEntity<List<Map<String, Double>>> simulatePrices(
//            @RequestParam(name = "days") Optional<Integer> daysSimulatedParam,
//            @RequestParam(name = "stocks") Optional<Integer> stockCountParam){
//        int daysSimulated = daysSimulatedParam.orElse(DEFAULT_DAYS_SIMULATED);
//        int stockCount = stockCountParam.orElse(DEFAULT_STOCK_COUNT);
//
//        return ResponseEntity.ok(marketSimulationManager.simulate(daysSimulated, stockCount)
//                .stream().map(stock -> new SimulatedStockDTO(stock).getMap()).toList());
//    }
//
    @GetMapping("/testing")
    public ResponseEntity<List<List<Map.Entry<Integer, Double>>>> testAll(){
        PricingModel model = new PricingModel.Builder(new Stock())
                .details(140.0, PriceVolatility.NORMAL)
                .innovation(20, .2, .25)
                .investorConfidence(20, .2, .25)
                .newsSentiment(20, .2, .25)
                .tradingDemand(20, .2, .25)
                .liquidity(20, .2, .25)
                .build();
        PricingModel model2 = new PricingModel.Builder(new Stock())
                .details(130.0, PriceVolatility.LOW)
                .innovation(-5, .2, .25)
                .investorConfidence(15, .2, .25)
                .newsSentiment(-10, .2, .25)
                .tradingDemand(2, .2, .25)
                .liquidity(21, .2, .25)
                .build();
        PricingModel model3 = new PricingModel.Builder(new Stock())
                .details(120.0, PriceVolatility.EXTREME)
                .innovation(-10, .2, .25)
                .investorConfidence(-25, .2, .25)
                .newsSentiment(-10, .2, .25)
                .tradingDemand(15, .2, .25)
                .liquidity(1, .2, .25)
                .build();
        PricingModel model4 = new PricingModel.Builder(new Stock())
                .details(110.0, PriceVolatility.HIGH)
                .innovation(-20, .2, .25)
                .investorConfidence(50, .2, .25)
                .newsSentiment(5, .2, .25)
                .tradingDemand(10, .2, .25)
                .liquidity(2, .2, .25)
                .build();

        Map<Integer, Double> map = new HashMap<>();
        Map<Integer, Double> map2 = new HashMap<>();
        Map<Integer, Double> map3 = new HashMap<>();
        Map<Integer, Double> map4 = new HashMap<>();

        int counter = 1;
        map.put(counter, model.getPriceValue());
        map2.put(counter, model2.getPriceValue());
        map3.put(counter, model3.getPriceValue());
        map4.put(counter, model4.getPriceValue());

        counter++;

        Instant time = Instant.now();
        for(int i = 0; i <= 10000; i++){
            model.runPriceChange();
            model2.runPriceChange();
            model3.runPriceChange();
            model4.runPriceChange();

            if(i % 100 == 0){
                map.put(counter, model.getPriceValue());
                map2.put(counter, model2.getPriceValue());
                map3.put(counter, model3.getPriceValue());
                map4.put(counter, model4.getPriceValue());

                counter++;
            }
        }
        Instant end = Instant.now();

        var l1 = map.entrySet().stream().toList().stream().sorted((e, i) -> e.getKey()).toList();
        var l2 = map2.entrySet().stream().toList().stream().sorted((e, i) -> e.getKey()).toList();
        var l3 = map3.entrySet().stream().toList().stream().sorted((e, i) -> e.getKey()).toList();
        var l4 = map4.entrySet().stream().toList().stream().sorted((e, i) -> e.getKey()).toList();
        List<Map.Entry<Integer, Double>> t = Map.of(-1, (double)Duration.between(time, end)
                        .toMillis()).entrySet().stream().toList();
        return ResponseEntity.ok(List.of(l1, l2, l3, l4));
    }
//
//    @PostMapping
//    public ResponseEntity<?> simulatePrices(@RequestBody SimulationRequest req){
//        if(Volatility.map(req.getVolatility()) == null || InvestorRating.map(req.getRating()) == null){
//            return ResponseEntity.badRequest().body("Invalid");
//        }
//        SimulatedStock stock = marketSimulationManager.simulate(req);
//        return ResponseEntity.ok(new SimulatedStockDTO(stock).getMap());
//    }
}
