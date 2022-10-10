package org.api.stockmarket.stocks.stock.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.stockmarket.stocks.earnings.entity.EarningsReport;
import org.api.stockmarket.stocks.news.entity.News;
import org.api.stockmarket.stocks.stock.enums.MarketCap;

import javax.persistence.*;
import java.util.List;

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
    private Boolean volatileStock;

    @OneToMany(mappedBy = "stock", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<News> newsHistory;

    @OneToMany(mappedBy = "stock", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<EarningsReport> earningsHistory;

    public Stock(
            String ticker,
            String companyName,
            String sector,
            MarketCap marketCap,
            double price
    ) {
        this.ticker = ticker;
        this.companyName = companyName;
        this.sector = sector;
        this.marketCap = marketCap;
        this.price = price;
    }

    public void updateMomentum() {
        int momentumStreak = getMomentumStreakInDays();
        if (momentumStreak >= 7) {
            setMomentum(2);
            return;
        }
        if (momentumStreak >= 3) {
            setMomentum(1);
            return;
        }
        if (momentumStreak <= -7) {
            setMomentum(-2);
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
        if (getPrice() > getLastDayPrice()) {
            setMomentumStreakInDays(getMomentumStreakInDays() + 1);
            return;
        }
        if (getPrice() < getLastDayPrice()) {
            setMomentumStreakInDays(getMomentumStreakInDays() - 1);
        }
    }
}