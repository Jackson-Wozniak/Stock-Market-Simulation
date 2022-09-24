package stocktradingsimulator.stock.model.object;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stocktradingsimulator.stock.defaults.DefaultStockPrices;
import stocktradingsimulator.stock.model.entity.Stock;

import javax.persistence.Entity;

/*
    Entity To save Default Stocks to database
    Sets prices and momentum to default values
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class DefaultStock extends Stock {

    private String ticker;
    private String companyName;
    private String sector;
    private String marketCap;
    private Double price;
    private Double lastDayPrice;
    private Integer momentum;
    private Integer momentumStreakInDays;
    private Boolean volatileStock;

    public DefaultStock(String ticker,
                 String companyName,
                 String sector,
                 String marketCap,
                 boolean volatileStock){
        this.ticker = ticker;
        this.companyName = companyName;
        this.sector = sector;
        this.marketCap = marketCap;
        this.volatileStock = volatileStock;
        this.price = DefaultStockPrices.getDefaultPriceWithCap(marketCap);
        this.lastDayPrice = 0.0;
        this.momentum = 0;
        this.momentumStreakInDays = 0;
    }
}
