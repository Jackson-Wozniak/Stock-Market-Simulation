package stocktradingsimulator.market;

import org.springframework.stereotype.Component;
import stocktradingsimulator.stock.Stock;

import java.text.DecimalFormat;

@Component
public class ChangeStockPrices {

    private static final DecimalFormat decimalFormat = new DecimalFormat("0.000");

    public double automaticPriceChange(Stock stock){
        return switch (stock.getMarketCap().toLowerCase()){
            case "large" -> Double.parseDouble(changeStockPrice(stock, "large"));
            case "mid" -> Double.parseDouble(changeStockPrice(stock, "mid"));
            case "small" -> Double.parseDouble(changeStockPrice(stock, "small"));
            default -> stock.getPrice();
        };
    }

    private String changeStockPrice(Stock stock, String marketCap){
        //Volatile stocks change twice to increase market movements
        if(stock.getVolatileStock()){
            return decimalFormat.format(stock.getPrice() +
                    (stock.getPrice() * GetRandomNumber.getRandomNumberForStocks(marketCap)) +
                    (stock.getPrice() * GetRandomNumber.getRandomNumberForStocks(marketCap)) +
                    (stock.getOptimism() * GetRandomNumber.getRandomPositiveNumberForStocks(marketCap)));
        }
        return decimalFormat.format(stock.getPrice() +
                (stock.getPrice() * GetRandomNumber.getRandomNumberForStocks(marketCap)) +
                (stock.getOptimism() * GetRandomNumber.getRandomPositiveNumberForStocks(marketCap)));
    }
}
