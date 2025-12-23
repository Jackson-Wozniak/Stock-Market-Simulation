package org.api.stockmarket.modules.stocks.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.stockmarket.core.entity.BaseEntity;
import org.api.stockmarket.modules.news.entity.NewsRelease;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "stock")
@Table(name = "stocks")
@Getter
@Setter
@NoArgsConstructor
public class Stock extends BaseEntity {
    @Column(name = "ticker")
    private String ticker;

    @OneToOne(mappedBy = "stock", cascade = CascadeType.ALL, orphanRemoval = true)
    private Company company;

    @OneToOne(mappedBy = "stock", cascade = CascadeType.ALL, orphanRemoval = true)
    private PricingModel pricingModel;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<NewsRelease> newsReleases = new ArrayList<>();

    @OneToMany(mappedBy = "stock", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PriceRecord> priceRecords = new ArrayList<>();

    public Stock(String ticker) {
        this.ticker = ticker;
    }

    public double getPrice(){
        return this.pricingModel.getPriceValue();
    }

    public void runPriceChange(){
        this.pricingModel.runPriceChange();
    }

    /*
    I wanted to separate this to be able to call momentum change methods
    so that I could make those private to avoid being called in other classes.
     */
//    public void momentumChange(){
//        updateMomentumStreak();
//        updateMomentum();
//    }
//
//    private void updateMomentum() {
//        int momentumStreak = getMomentumStreakInDays();
//        if (momentumStreak >= 3) {
//            setMomentum(1);
//            return;
//        }
//        if (momentumStreak <= -3) {
//            setMomentum(-1);
//            return;
//        }
//        setMomentum(0);
//    }
//
//    private void updateMomentumStreak() {
//        if (getMomentumStreakInDays() == null) {
//            setMomentumStreakInDays(0);
//            return;
//        }
//        double price = getPrice();
//        int momentumStreakDays = getMomentumStreakInDays();
//        if (price > getLastDayPrice()) {
//            if(momentumStreakDays <= -1){
//                setMomentumStreakInDays(0);
//                return;
//            }
//            setMomentumStreakInDays(momentumStreakDays + 1);
//            return;
//        }
//        if (price < getLastDayPrice()) {
//            if(getMomentum() >= 1){
//                setMomentumStreakInDays(0);
//                return;
//            }
//            setMomentumStreakInDays(momentumStreakDays - 1);
//        }
//    }
//
//    /*
//    I wanted to seperate this to be able to call increase/decreaseInvestor rating
//    so that I could make those private to avoid being called in other classes.
//     */
//    public void newsEvent(boolean isPositive){
//        if(isPositive){
//            increaseInvestorRating();
//            return;
//        }
//        decreaseInvestorRating();
//    }
//
//    private void increaseInvestorRating(){
//        switch (this.getInvestorRating()){
//            case Sell -> this.setInvestorRating(InvestorRating.Hold);
//            case Hold -> this.setInvestorRating(InvestorRating.Neutral);
//            case Neutral -> this.setInvestorRating(InvestorRating.Buy);
//            case Buy -> this.setInvestorRating(InvestorRating.StrongBuy);
//            case StrongBuy -> {}
//        }
//    }
//
//    private void decreaseInvestorRating(){
//        switch (this.getInvestorRating()){
//            case Sell -> {}
//            case Hold -> this.setInvestorRating(InvestorRating.Sell);
//            case Neutral -> this.setInvestorRating(InvestorRating.Hold);
//            case Buy -> this.setInvestorRating(InvestorRating.Neutral);
//            case StrongBuy -> this.setInvestorRating(InvestorRating.Buy);
//        }
//    }
}
