package io.github.stockmarket.simulation.response;

import io.github.stockmarket.market.stocks.model.PriceRecord;
import io.github.stockmarket.simulation.model.SimulationEnvironment;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class SimulationResponse {
    private Map<String, List<Double>> stockPriceRecords = new HashMap<>();

    public SimulationResponse(SimulationEnvironment environment){
        environment.getMarketContext().getStocks().forEach(stock -> {
            stockPriceRecords.put(stock.getTicker(),
                    stock.getPriceRecords()
                            .stream()
                            .map(PriceRecord::getStockPrice)
                            .toList());
        });
    }
}
