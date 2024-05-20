package org.api.stockmarket.stocks.stock.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.stockmarket.market.utils.GetRandomNumber;
import org.api.stockmarket.stocks.earnings.entity.EarningsReport;
import org.api.stockmarket.stocks.news.entity.News;
import org.api.stockmarket.stocks.stock.defaults.DefaultStockPrices;
import org.api.stockmarket.stocks.stock.enums.InvestorRating;
import org.api.stockmarket.stocks.stock.enums.MarketCap;
import org.api.stockmarket.stocks.stock.enums.Volatility;

import jakarta.persistence.*;
import java.util.List;

@Entity(name = "stock")
@Table(name = "stock")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
/*
TODO:
    - refactor stock price history (called StockHistory now) to be included in the stock instead of
        a separate entity that must be queried for separately, will be OneToMany relationship
 */
public class Stock{

    @Id
    private String ticker;

    @Column(name = "name")
    private String companyName;

    @Column(name = "sector")
    private String sector;

    @Column(name = "cap")
    @Enumerated(EnumType.STRING)
    private MarketCap marketCap;

    @Column(name = "price")
    private Double price;

    @Column(name = "last_day_price")
    private Double lastDayPrice;

    @Column(name = "momentum")
    private Integer momentum;

    @Column(name = "momentum_streak")
    private Integer momentumStreakInDays;

    @Column(name = "volatile")
    @Enumerated(EnumType.STRING)
    private Volatility volatileStock;

    @Column(name = "investor_rating")
    @Enumerated(EnumType.STRING)
    private InvestorRating investorRating;

    @OneToMany(mappedBy = "stock", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<News> newsHistory;

    @OneToMany(mappedBy = "stock", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<EarningsReport> earningsHistory;

    @OneToMany(mappedBy = "stock", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<StockPriceHistory> priceHistory;

    public Stock(String ticker,
                        String companyName,
                        String sector,
                        MarketCap marketCap,
                        Volatility volatileStock,
                        InvestorRating investorRating) {
        this.ticker = ticker;
        this.companyName = companyName;
        this.sector = sector;
        this.marketCap = marketCap;
        this.volatileStock = volatileStock;
        this.investorRating = investorRating;
        this.price = DefaultStockPrices.getDefaultPriceWithCap(marketCap);
        this.lastDayPrice = DefaultStockPrices.getDefaultPriceWithCap(marketCap);
        this.momentum = 0;
        this.momentumStreakInDays = 0;
    }

    public void updatePriceWithFormula(){
        //Volatile stocks change twice to increase market movements
        double randomNumber = GetRandomNumber.getRandomNumberForStocks(this.marketCap);
        double randomPositiveNumber = GetRandomNumber.getRandomPositiveNumberForStocks(this.marketCap);
        double stockPrice = this.getPrice();
        double newPrice = Math.round((stockPrice +
                (stockPrice * randomNumber) +
                (stockPrice * (randomNumber * this.getVolatileStock().ordinal())) +
                (this.getInvestorRating().investorRatingMultiplier() * randomPositiveNumber) +
                (this.getMomentum() * randomPositiveNumber)) * 100.00 ) / 100.00;
        setPrice(newPrice);
    }

    public void updateMomentum() {
        int momentumStreak = getMomentumStreakInDays();
        if (momentumStreak >= 3) {
            setMomentum(1);
            return;
        }
        if (momentumStreak <= -3) {
            setMomentum(-1);
            return;
        }
        setMomentum(0);
    }

    public void updateMomentumStreak() {
        if (getMomentumStreakInDays() == null) {
            setMomentumStreakInDays(0);
            return;
        }
        double price = getPrice();
        int momentumStreakDays = getMomentumStreakInDays();
        if (price > getLastDayPrice()) {
            if(momentumStreakDays <= -1){
                setMomentumStreakInDays(0);
                return;
            }
            setMomentumStreakInDays(momentumStreakDays + 1);
            return;
        }
        if (price < getLastDayPrice()) {
            if(getMomentum() >= 1){
                setMomentumStreakInDays(0);
                return;
            }
            setMomentumStreakInDays(momentumStreakDays - 1);
        }
    }

    //these two methods are called only on news and earnings report announcements
    public void increaseInvestorRating(){
        switch (this.getInvestorRating()){
            case Sell -> this.setInvestorRating(InvestorRating.Hold);
            case Hold -> this.setInvestorRating(InvestorRating.Neutral);
            case Neutral -> this.setInvestorRating(InvestorRating.Buy);
            case Buy -> this.setInvestorRating(InvestorRating.StrongBuy);
            case StrongBuy -> {}
        }
    }

    public void decreaseInvestorRating(){
        switch (this.getInvestorRating()){
            case Sell -> {}
            case Hold -> this.setInvestorRating(InvestorRating.Sell);
            case Neutral -> this.setInvestorRating(InvestorRating.Hold);
            case Buy -> this.setInvestorRating(InvestorRating.Neutral);
            case StrongBuy -> this.setInvestorRating(InvestorRating.Buy);
        }
    }
}