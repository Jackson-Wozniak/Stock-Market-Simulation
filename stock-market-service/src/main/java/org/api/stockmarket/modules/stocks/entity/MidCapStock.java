package org.api.stockmarket.stocks.stock.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.api.stockmarket.stocks.stock.enums.InvestorRating;
import org.api.stockmarket.stocks.stock.enums.MarketCap;
import org.api.stockmarket.stocks.stock.enums.Volatility;
import org.api.stockmarket.stocks.stock.properties.DefaultStockPrices;

import java.util.Random;

@Entity
@DiscriminatorValue(value = "MID_CAP")
public class MidCapStock extends Stock{

    private static final Random random = new Random();

    public MidCapStock(String ticker,
                         String companyName,
                         String sector,
                         Volatility volatileStock,
                         InvestorRating investorRating) {
        super(ticker, companyName, sector, MarketCap.Mid,
                DefaultStockPrices.DEFAULT_MID_CAP_PRICE, volatileStock, investorRating);
    }

    public MidCapStock() {

    }

    @Override
    public void updatePrice() {
        //Volatile stocks change twice to increase market movements
        double price = getPrice();
        double interval = -.001 + (.001 - (-.001)) * random.nextDouble();
        double positiveInterval = .0008 * random.nextDouble();
        double newPrice = getPrice() +
                (price * interval) +
                (price * (interval * this.getVolatileStock().ordinal())) +
                (price * (positiveInterval * this.getInvestorRating().investorRatingMultiplier())) +
                (price * (positiveInterval * this.getMomentum()));
        setPrice(Math.round(newPrice * 100.00) / 100.00);
    }
}
