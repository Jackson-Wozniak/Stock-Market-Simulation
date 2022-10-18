package org.api.stockmarket.stocks.stock.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.stockmarket.market.utils.GetRandomNumber;
import org.api.stockmarket.stocks.stock.defaults.DefaultStockPrices;
import org.api.stockmarket.stocks.stock.enums.InvestorRating;
import org.api.stockmarket.stocks.stock.enums.Volatility;
import org.api.stockmarket.stocks.earnings.entity.EarningsReport;
import org.api.stockmarket.stocks.news.entity.News;
import org.api.stockmarket.stocks.stock.enums.MarketCap;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.util.*;

@Entity(name = "stock")
@Table(name = "stock")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
public class Stock{

    @Id
    private String ticker;

    @Column(name = "name")
    private String companyName;

    @Column(name = "sector")
    private String sector;

    @Column(name = "cap")
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
    private Volatility volatileStock;

    @Column(name = "investor_rating")
    private InvestorRating investorRating;

    @OneToMany(mappedBy = "stock", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<News> newsHistory;

    @OneToMany(mappedBy = "stock", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<EarningsReport> earningsHistory;

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
        double stockPrice = this.getPrice();
        double randomNumber = GetRandomNumber.getRandomNumberForStocks(this.marketCap);
        double randomPositiveNumber = GetRandomNumber.getRandomSmallPositiveNumber();
        this.setPrice(
                Math.round((stockPrice +
                        (stockPrice * randomNumber) +
                        (stockPrice * (randomNumber * this.volatileStock.ordinal())) +
                        (stockPrice * (randomPositiveNumber * this.investorRating.investorRatingMultiplier())) +
                        (stockPrice * (randomPositiveNumber * this.momentum))
                ) * 100.00) / 100.00);
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
        if (price > getLastDayPrice()) {
            if(getMomentumStreakInDays() <= -1){
                setMomentumStreakInDays(0);
                return;
            }
            setMomentumStreakInDays(getMomentumStreakInDays() + 1);
            return;
        }
        if (price < getLastDayPrice()) {
            if(getMomentum() >= 1){
                setMomentumStreakInDays(0);
                return;
            }
            setMomentumStreakInDays(getMomentumStreakInDays() - 1);
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