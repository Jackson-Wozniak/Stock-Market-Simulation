package stocktradingsimulator.market.scheduled;

import org.springframework.stereotype.Component;
import stocktradingsimulator.market.utils.GetRandomNumber;
import stocktradingsimulator.stock.enums.MarketCap;
import stocktradingsimulator.stock.model.entity.Stock;

import java.text.DecimalFormat;

@Component
public class ChangeStockPrices {

    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public double automaticPriceChange(Stock stock){
        return switch (stock.getMarketCap()){
            case Large -> Double.parseDouble(changeStockPrice(stock, MarketCap.Large));
            case Mid -> Double.parseDouble(changeStockPrice(stock, MarketCap.Mid));
            case Small -> Double.parseDouble(changeStockPrice(stock, MarketCap.Small));
        };
    }

    private String changeStockPrice(Stock stock, MarketCap marketCap){
        //Volatile stocks change twice to increase market movements
        if(stock.getVolatileStock()){
            return decimalFormat.format(stock.getPrice() +
                    (stock.getPrice() * GetRandomNumber.getRandomNumberForStocks(marketCap)) +
                    (stock.getPrice() * GetRandomNumber.getRandomNumberForStocks(marketCap)) +
                    (stock.getMomentum() * GetRandomNumber.getRandomPositiveNumberForStocks(marketCap)));
        }
        return decimalFormat.format(stock.getPrice() +
                (stock.getPrice() * GetRandomNumber.getRandomNumberForStocks(marketCap)) +
                (stock.getMomentum() * GetRandomNumber.getRandomPositiveNumberForStocks(marketCap)));
    }
}
