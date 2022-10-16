package org.api.stockmarket.market.scheduled;

import org.api.stockmarket.market.utils.GetRandomNumber;
import org.api.stockmarket.stocks.stock.enums.MarketCap;
import org.api.stockmarket.stocks.stock.model.entity.Stock;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

@Component
public class ChangeStockPrices {

    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public double automaticPriceChange(Stock stock) {
        return switch (stock.getMarketCap()) {
            case Large -> Double.parseDouble(changeStockPrice(stock, MarketCap.Large));
            case Mid -> Double.parseDouble(changeStockPrice(stock, MarketCap.Mid));
            case Small -> Double.parseDouble(changeStockPrice(stock, MarketCap.Small));
        };
    }

    private String changeStockPrice(Stock stock, MarketCap marketCap) {
        double stockPrice = stock.getPrice();
        return decimalFormat.format(stockPrice +
                (stockPrice * GetRandomNumber.getRandomNumberForStocks(marketCap)) +
                (stockPrice * (GetRandomNumber.getRandomSmallNumber() * stock.getVolatileStock().ordinal()) +
                (stock.getMomentum() * GetRandomNumber.getRandomPositiveNumberForStocks(marketCap))));
    }
}
