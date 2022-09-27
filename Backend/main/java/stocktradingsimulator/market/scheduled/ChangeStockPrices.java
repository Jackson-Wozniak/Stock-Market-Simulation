package stocktradingsimulator.market.scheduled;

import org.springframework.stereotype.Component;
import stocktradingsimulator.market.utils.GetRandomNumber;
import stocktradingsimulator.stocks.stock.enums.MarketCap;
import stocktradingsimulator.stocks.stock.model.entity.Stock;

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
        double randomNumber = GetRandomNumber.getRandomNumberForStocks(marketCap);
        double stockPrice = stock.getPrice();
        if(stock.getVolatileStock()){
            return decimalFormat.format(stockPrice +
                    (stockPrice * randomNumber) +
                    (stockPrice * randomNumber) +
                    (stock.getMomentum() * GetRandomNumber.getRandomPositiveNumberForStocks(marketCap)));
        }
        return decimalFormat.format(stockPrice +
                (stockPrice * randomNumber) +
                (stock.getMomentum() * GetRandomNumber.getRandomPositiveNumberForStocks(marketCap)));
    }
}
