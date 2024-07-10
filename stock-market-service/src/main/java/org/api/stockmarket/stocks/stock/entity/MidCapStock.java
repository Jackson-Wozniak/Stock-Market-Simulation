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
        double interval = random.nextDouble(-.001, .001);
        double positiveInterval = random.nextDouble(0, .0008);
        double newPrice = Math.round((getPrice() +
                (getPrice() * interval) +
                (getPrice() * (interval * this.getVolatileStock().ordinal())) +
                (this.getInvestorRating().investorRatingMultiplier() * positiveInterval) +
                (this.getMomentum() * positiveInterval)) * 100.00 ) / 100.00;
        setPrice(newPrice);
    }
}
