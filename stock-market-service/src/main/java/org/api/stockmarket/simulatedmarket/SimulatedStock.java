package org.api.stockmarket.simulator;

import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.stocks.stock.entity.Stock;
import org.api.stockmarket.stocks.stock.enums.InvestorRating;
import org.api.stockmarket.stocks.stock.enums.Volatility;

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

    public static SimulatedStock defaultInfo(){
        return new SimulatedStock(Stock.largeCap("DEFAULT", "DEFAULT",
                "DEFAULT", Volatility.NORMAL, InvestorRating.Neutral));
    }

    public static SimulatedStock customInfo(Volatility volatility, InvestorRating rating){
        return new SimulatedStock(Stock.largeCap("CUSTOM", "CUSTOM",
                "CUSTOM", volatility, rating));
    }

    public void changePrice(){
        this.stock.updatePrice();
    }

    public void savePrice(LocalDate date){
        this.priceHistory.put(date, stock.getPrice());
    }
}
