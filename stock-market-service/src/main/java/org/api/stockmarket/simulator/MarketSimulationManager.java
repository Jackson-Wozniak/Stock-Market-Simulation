package org.api.stockmarket.simulator;

import org.api.stockmarket.market.properties.MarketIntervals;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class MarketSimulationManager {

    public List<SimulatedStock> simulate(int days, int stockCount){
        List<SimulatedStock> stocks = IntStream.range(0, stockCount)
                .mapToObj(i -> SimulatedStock.defaultInfo())
                .toList();
        LocalDate date = LocalDate.now();
        for(int i = 0; i < days; i++){
            for(int j = 0; j < MarketIntervals.PRICE_CHANGES_PER_DAY; j++){
                stocks.forEach(SimulatedStock::changePrice);
            }
            LocalDate dateCopy = date;
            stocks.forEach(stock -> stock.savePrice(dateCopy));
            date = date.plusDays(1);
        }
        return stocks;
    }
}
