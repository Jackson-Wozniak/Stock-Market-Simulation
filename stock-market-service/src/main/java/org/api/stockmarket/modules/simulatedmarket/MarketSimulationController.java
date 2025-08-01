package org.api.stockmarket.modules.simulatedmarket;

import lombok.AllArgsConstructor;
import org.api.stockmarket.modules.stocks.entity.PricingModel;
import org.api.stockmarket.modules.stocks.enums.InvestorRating;
import org.api.stockmarket.modules.stocks.enums.PriceVolatility;
import org.api.stockmarket.modules.stocks.enums.Volatility;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/market/sim")
@AllArgsConstructor
@CrossOrigin(origins = "*")
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
    @GetMapping
    public ResponseEntity<List<Map<String, Double>>> simulatePrices(
            @RequestParam(name = "days") Optional<Integer> daysSimulatedParam,
            @RequestParam(name = "stocks") Optional<Integer> stockCountParam){
        int daysSimulated = daysSimulatedParam.orElse(DEFAULT_DAYS_SIMULATED);
        int stockCount = stockCountParam.orElse(DEFAULT_STOCK_COUNT);

        return ResponseEntity.ok(marketSimulationManager.simulate(daysSimulated, stockCount)
                .stream().map(stock -> new SimulatedStockDTO(stock).getMap()).toList());
    }

//    @GetMapping("/testing")
//    public ResponseEntity<String> testAll(){
//        PricingModel model = new PricingModel(100.0, PriceVolatility.STABLE,
//                50, .2, .005,
//                -30, .2, .2,
//                50, .2, .005,
//                50, .2, .005,
//                50, .2, .005);
//        PricingModel model2 = new PricingModel(100.0, PriceVolatility.EXTREME,
//                50, .2, .005,
//                -12, .2, .25,
//                50, .2, .005,
//                50, .2, .005,
//                50, .2, .005);
//        PricingModel model3 = new PricingModel(100.0, PriceVolatility.NORMAL,
//                50, .2, .005,
//                5, .2, .25,
//                50, .2, .005,
//                50, .2, .005,
//                50, .2, .005);
//        PricingModel model4 = new PricingModel(100.0, PriceVolatility.HIGH,
//                50, .2, .005,
//                40, .2, .25,
//                50, .2, .005,
//                50, .2, .005,
//                50, .2, .005);
//
//        String begin = String.valueOf(model.getCurrentPrice());
//        String begin2 = String.valueOf(model.getCurrentPrice());
//        String begin3 = String.valueOf(model.getCurrentPrice());
//        String begin4 = String.valueOf(model.getCurrentPrice());
//
//        StringBuilder builder = new StringBuilder();
//        for(int i = 0; i < 10000; i++){
//            model.changePrice();
//            model2.changePrice();
//            model3.changePrice();
//            model4.changePrice();
//
//            if(i % 1000 == 0){
//                builder.append("Price Change: 1" + begin
//                                + " -> " + model.getCurrentPrice())
//                        .append("\nPrice Change 2: " + begin2
//                                        + " -> " + model2.getCurrentPrice())
//                        .append("\nPrice Change 3: "
//                                        + begin3 + " -> " + model3.getCurrentPrice())
//                        .append("\nPrice Change 4: " + begin4 + " -> "
//                                + model4.getCurrentPrice()).append("\n");
//            }
//        }
//
//        builder.append("Price Change: 1" + begin
//                        + " -> " + model.getCurrentPrice())
//                .append("\nPrice Change 2: " + begin2
//                        + " -> " + model2.getCurrentPrice())
//                .append("\nPrice Change 3: "
//                        + begin3 + " -> " + model3.getCurrentPrice())
//                .append("\nPrice Change 4: " + begin4 + " -> "
//                        + model4.getCurrentPrice());
//        return ResponseEntity.ok(builder.toString());
//    }

    @PostMapping
    public ResponseEntity<?> simulatePrices(@RequestBody SimulationRequest req){
        if(Volatility.map(req.getVolatility()) == null || InvestorRating.map(req.getRating()) == null){
            return ResponseEntity.badRequest().body("Invalid");
        }
        SimulatedStock stock = marketSimulationManager.simulate(req);
        return ResponseEntity.ok(new SimulatedStockDTO(stock).getMap());
    }
}
