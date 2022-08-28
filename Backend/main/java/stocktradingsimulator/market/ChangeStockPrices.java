package stocktradingsimulator.market;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import stocktradingsimulator.stock.Stock;

import java.text.DecimalFormat;

@Component
@AllArgsConstructor
public class ChangeStockPrices {

    @Autowired
    private final GetRandomNumber getRandomNumber;
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.000");

    public double automaticPriceChange(Stock stock){
        return switch (stock.getMarketCap().toLowerCase()){
            case "large" -> Double.parseDouble(changeLargeCapPrice(stock));
            case "mid" -> Double.parseDouble(changeMidCapPrice(stock));
            case "small" -> Double.parseDouble(changeSmallCapPrice(stock));
            default -> stock.getPrice();
        };
    }

    private String changeLargeCapPrice(Stock stock){
        //if volatile, price changes twice
        if(stock.getVolatileStock()){
            return decimalFormat.format(stock.getPrice() +
                    (stock.getPrice() * getRandomNumber.getRandomNumberForLargeCap()) +
                    (stock.getPrice() * getRandomNumber.getRandomNumberForLargeCap()) +
                    (stock.getOptimism() * getRandomNumber.getRandomPositiveNumberForLargeCap()));
        }
        return decimalFormat.format(stock.getPrice() +
                (stock.getPrice() * getRandomNumber.getRandomNumberForLargeCap()) +
                (stock.getOptimism() * getRandomNumber.getRandomPositiveNumberForLargeCap()));
    }

    private String changeMidCapPrice(Stock stock){
        //if volatile, price changes twice
        if(stock.getVolatileStock()){
            return decimalFormat.format(stock.getPrice() +
                    (stock.getPrice() * getRandomNumber.getRandomNumberForMidCap()) +
                    (stock.getPrice() * getRandomNumber.getRandomNumberForMidCap()) +
                    (stock.getOptimism() * getRandomNumber.getRandomPositiveNumberForMidCap()));
        }
        return decimalFormat.format(stock.getPrice() +
                (stock.getPrice() * getRandomNumber.getRandomNumberForMidCap()) +
                (stock.getOptimism() * getRandomNumber.getRandomPositiveNumberForMidCap()));
    }

    private String changeSmallCapPrice(Stock stock){
        //if volatile, price changes twice
        if(stock.getVolatileStock()){
            return decimalFormat.format(stock.getPrice() +
                    (stock.getPrice() * getRandomNumber.getRandomNumberForSmallCap()) +
                    (stock.getPrice() * getRandomNumber.getRandomNumberForSmallCap()) +
                    (stock.getOptimism() * getRandomNumber.getRandomPositiveNumberForSmallCap()));
        }
        return decimalFormat.format(stock.getPrice() +
                (stock.getPrice() * getRandomNumber.getRandomNumberForSmallCap()) +
                (stock.getOptimism() * getRandomNumber.getRandomPositiveNumberForSmallCap()));
    }
}
