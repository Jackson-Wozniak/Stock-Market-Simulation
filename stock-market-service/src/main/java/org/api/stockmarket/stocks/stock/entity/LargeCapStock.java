package org.api.stockmarket.stocks.stock.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.api.stockmarket.stocks.stock.enums.InvestorRating;
import org.api.stockmarket.stocks.stock.enums.MarketCap;
import org.api.stockmarket.stocks.stock.enums.Volatility;
import org.api.stockmarket.stocks.stock.properties.DefaultStockPrices;

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
        double interval = -.002 + (.002 - (-.002)) * random.nextDouble();
        double positiveInterval = .0018 * random.nextDouble();
        double newPrice = Math.round((getPrice() +
                (getPrice() * interval) +
                (getPrice() * (interval * this.getVolatileStock().ordinal())) +
                (this.getInvestorRating().investorRatingMultiplier() * positiveInterval) +
                (this.getMomentum() * positiveInterval)) * 100.00 ) / 100.00;
        setPrice(newPrice);
    }
}
