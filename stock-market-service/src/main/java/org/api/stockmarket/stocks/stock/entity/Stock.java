package org.api.stockmarket.stocks.stock.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.stockmarket.stocks.earnings.entity.EarningsReport;
import org.api.stockmarket.stocks.news.entity.News;
import org.api.stockmarket.stocks.stock.enums.InvestorRating;
import org.api.stockmarket.stocks.stock.enums.MarketCap;
import org.api.stockmarket.stocks.stock.enums.Volatility;

import jakarta.persistence.*;
import java.util.List;

@Entity(name = "stock")
@Table(name = "stock")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "STOCK_TYPE", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
public abstract class Stock{

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

    protected Stock(String ticker,
                        String companyName,
                        String sector,
                        MarketCap marketCap,
                        double defaultPrice,
                        Volatility volatileStock,
                        InvestorRating investorRating) {
        this.ticker = ticker;
        this.companyName = companyName;
        this.sector = sector;
        this.marketCap = marketCap;
        this.volatileStock = volatileStock;
        this.investorRating = investorRating;
        this.price = defaultPrice;
        this.lastDayPrice = defaultPrice;
        this.momentum = 0;
        this.momentumStreakInDays = 0;
    }

    public abstract void updatePrice();

    /*
    I wanted to separate this to be able to call momentum change methods
    so that I could make those private to avoid being called in other classes.
     */
    public void momentumChange(){
        updateMomentumStreak();
        updateMomentum();
    }

    private void updateMomentum() {
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

    private void updateMomentumStreak() {
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

    /*
    I wanted to seperate this to be able to call increase/decreaseInvestor rating
    so that I could make those private to avoid being called in other classes.
     */
    public void newsEvent(boolean isPositive){
        if(isPositive){
            increaseInvestorRating();
            return;
        }
        decreaseInvestorRating();
    }

    private void increaseInvestorRating(){
        switch (this.getInvestorRating()){
            case Sell -> this.setInvestorRating(InvestorRating.Hold);
            case Hold -> this.setInvestorRating(InvestorRating.Neutral);
            case Neutral -> this.setInvestorRating(InvestorRating.Buy);
            case Buy -> this.setInvestorRating(InvestorRating.StrongBuy);
            case StrongBuy -> {}
        }
    }

    private void decreaseInvestorRating(){
        switch (this.getInvestorRating()){
            case Sell -> {}
            case Hold -> this.setInvestorRating(InvestorRating.Sell);
            case Neutral -> this.setInvestorRating(InvestorRating.Hold);
            case Buy -> this.setInvestorRating(InvestorRating.Neutral);
            case StrongBuy -> this.setInvestorRating(InvestorRating.Buy);
        }
    }

    public static SmallCapStock smallCap(String ticker,
                                 String companyName,
                                 String sector,
                                 Volatility volatileStock,
                                 InvestorRating investorRating){
        return new SmallCapStock(ticker, companyName, sector, volatileStock, investorRating);
    }

    public static MidCapStock midCap(String ticker,
                                         String companyName,
                                         String sector,
                                         Volatility volatileStock,
                                         InvestorRating investorRating){
        return new MidCapStock(ticker, companyName, sector, volatileStock, investorRating);
    }

    public static LargeCapStock largeCap(String ticker,
                                         String companyName,
                                         String sector,
                                         Volatility volatileStock,
                                         InvestorRating investorRating){
        return new LargeCapStock(ticker, companyName, sector, volatileStock, investorRating);
    }
}
