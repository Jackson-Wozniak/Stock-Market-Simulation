package org.api.stockmarket.modules.stocks.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.api.stockmarket.modules.stocks.enums.InvestorRating;
import org.api.stockmarket.modules.stocks.enums.MarketCap;
import org.api.stockmarket.modules.stocks.enums.Volatility;
import org.api.stockmarket.modules.stocks.properties.DefaultStockPrices;

import java.util.Random;

@Entity
@DiscriminatorValue(value = "Large_CAP")
public class LargeCapStock extends Stock{

    private static final Random random = new Random();

    public LargeCapStock(String ticker,
                       String companyName,
                       String sector,
                       Volatility volatileStock,
                       InvestorRating investorRating) {
        super(ticker, companyName, sector, MarketCap.Large,
                DefaultStockPrices.DEFAULT_LARGE_CAP_PRICE, volatileStock, investorRating);
    }

    public LargeCapStock() {

    }

    @Override
    public void updatePrice() {
        //Volatile stocks change twice to increase market movements
        double price = getPrice();
        double interval = -.002 + (.002 - (-.002)) * random.nextDouble();
        double positiveInterval = .0018 * random.nextDouble();
        double newPrice = getPrice() +
                (price * interval) +
                (price * (interval * this.getVolatileStock().ordinal())) +
                (price * (positiveInterval * this.getInvestorRating().investorRatingMultiplier())) +
                (price * (positiveInterval * this.getMomentum()));
        setPrice(Math.round(newPrice * 100.00) / 100.00);
    }
}
