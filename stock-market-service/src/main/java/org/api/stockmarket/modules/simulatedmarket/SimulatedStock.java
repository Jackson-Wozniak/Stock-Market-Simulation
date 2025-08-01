package org.api.stockmarket.modules.simulatedmarket;

import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.modules.stocks.entity.Stock;
import org.api.stockmarket.modules.stocks.enums.InvestorRating;
import org.api.stockmarket.modules.stocks.enums.Volatility;

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
