package stocktradingsimulator.stock;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stocktradingsimulator.stock.utils.DefaultStockPrices;

import javax.persistence.Entity;

/*
    Entity To save Default Stocks to database
    Sets prices and optimism to default values
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class DefaultStock extends Stock{

    private String ticker;
    private String companyName;
    private String sector;
    private String marketCap;
    private Double price;
    private final Double lastDayPrice = 0.0;
    private final Integer optimism = 0;
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
    }
}
