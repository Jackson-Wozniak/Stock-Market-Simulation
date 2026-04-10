package io.github.stockmarket.market.simulatedmarket;

import lombok.Getter;
import lombok.Setter;
import io.github.stockmarket.market.stocks.model.Stock;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class SimulatedStock {

    private Stock stock;
    private Map<LocalDate, Double> priceHistory = new HashMap<>();

    private SimulatedStock(Stock stock){
        this.stock = stock;
    }
}
