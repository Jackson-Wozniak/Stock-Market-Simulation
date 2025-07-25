package org.api.stockmarket.modules.stocks.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.api.stockmarket.modules.stocks.enums.InvestorRating;
import org.api.stockmarket.modules.stocks.enums.MarketCap;
import org.api.stockmarket.modules.stocks.enums.Volatility;
import org.api.stockmarket.modules.stocks.properties.DefaultStockPrices;

import java.util.Random;

@Entity
@DiscriminatorValue(value = "SMALL_CAP")
public class SmallCapStock extends Stock{

    private static final Random random = new Random();

    protected SmallCapStock(String ticker,
                 String companyName,
                 String sector,
                 Volatility volatileStock,
                 InvestorRating investorRating) {
        super(ticker, companyName, sector, MarketCap.Small,
                DefaultStockPrices.DEFAULT_SMALL_CAP_PRICE, volatileStock, investorRating);
    }

    public SmallCapStock() {

    }

    @Override
    public void updatePrice() {
        //Volatile stocks change twice to increase market movements
        double price = getPrice();
        double interval = -.003 + (.003 - (-.003)) * random.nextDouble();
        double positiveInterval = .00028 * random.nextDouble();
        double newPrice = getPrice() +
                (price * interval) +
                (price * (interval * this.getVolatileStock().ordinal())) +
                (price * (positiveInterval * this.getInvestorRating().investorRatingMultiplier())) +
                (price * (positiveInterval * this.getMomentum()));
        setPrice(Math.round(newPrice * 100.00) / 100.00);
    }
}
