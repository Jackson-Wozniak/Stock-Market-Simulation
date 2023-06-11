package org.api.stockmarket.options.utils;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.api.stockmarket.options.entity.OptionContract;
import org.api.stockmarket.options.enums.OptionType;
import org.api.stockmarket.stocks.stock.entity.Stock;
import org.api.stockmarket.stocks.stock.enums.Volatility;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class OptionsPriceCalculator {

    private static final NormalDistribution normalDist = new NormalDistribution();
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    /*
    Variables:
        - new call/put price
        - price of current stock
        - strike price
        - time to expiration
        - risk-free rate (3.5%)
        - Normal Distribution

     */
    public static double blackSholesFormula(OptionContract option, String marketDate){
//        double impliedVolatility = .18;//findStockVolatility(option.getStock());
//        System.out.println((double) daysTillExpiration(marketDate, option.getExpirationDate()) / 365.0);
//        double yearsToExpiration = (double) daysTillExpiration(marketDate, option.getExpirationDate()) / 365.0;
//        double currentPrice = option.getStock().getPrice();
//        double strikePrice = option.getStrikePrice();
//        double riskFreeRate = .035;

        if(option.getOptionType().equals(OptionType.CALL)){
            double impliedVolatility = findStockVolatility(option.getStock());
            double yearsToExpiration = daysTillExpiration(marketDate, option.getExpirationDate()) / 365.0;
            double currentPrice = option.getStock().getPrice();
            double strikePrice = option.getStrikePrice();
            double riskFreeRate = .035;
            double denominator = (impliedVolatility * Math.sqrt(yearsToExpiration));
            double d1 = ((Math.log(currentPrice / strikePrice)
                    + (riskFreeRate + (Math.pow(impliedVolatility, 2) / 2)) * yearsToExpiration))
                    / denominator;
            double d2 = d1 - denominator;

            double price = (currentPrice * normalDist.cumulativeProbability(d1))
                    - (strikePrice * Math.exp(-riskFreeRate * yearsToExpiration)
                    * (normalDist.cumulativeProbability(d2)));
            return formatOptionPrice(price);
        }

        if(option.getOptionType().equals(OptionType.PUT)){
            double impliedVolatility = findStockVolatility(option.getStock());
            double yearsToExpiration = daysTillExpiration(marketDate, option.getExpirationDate()) / 365.0;
            double currentPrice = option.getStock().getPrice();
            double strikePrice = option.getStrikePrice();
            double riskFreeRate = .035;
            double denominator = (impliedVolatility * Math.sqrt(yearsToExpiration));
            double d1 = ((Math.log(currentPrice / strikePrice)
                    + (riskFreeRate + (Math.pow(impliedVolatility, 2) / 2)) * yearsToExpiration))
                    / denominator;
            double d2 = d1 - denominator;

            double price = (strikePrice * Math.exp(-riskFreeRate * yearsToExpiration)
                    * normalDist.cumulativeProbability(-d2))
                    - (currentPrice * normalDist.cumulativeProbability(-d1));
            return formatOptionPrice(price);
        }

        return 0.0;
    }

    public static double findStockVolatility(Stock stock){
        //this is the standard deviation before other factors are included (volatility value and time to earnings)
        double baseStandardDeviation = .08;

        return baseStandardDeviation * (Volatility.value(stock.getVolatileStock()) + 1);
    }

    private static double formatOptionPrice(double optionPrice){
        return Double.parseDouble(decimalFormat.format(optionPrice));
    }

    public static long daysTillExpiration(String marketDate, String expirationDate) {
        try{
            SimpleDateFormat dates = new SimpleDateFormat("MM/dd/yyyy");

            //Setting dates
            Date market = dates.parse(marketDate);
            Date expiration = dates.parse(expirationDate);

            //Comparing dates
            long difference = Math.abs(market.getTime() - expiration.getTime());

            return difference / (24 * 60 * 60 * 1000);
        }catch(Exception ex){
            throw new RuntimeException("Invalid date format");
        }
    }
}
