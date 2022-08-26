package stocktradingsimulator.stock;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity(name = "stock")
@Table(name = "stock")
@Getter
@Setter
@NoArgsConstructor
public class Stock {

    @Id
    private String ticker;

    @Column(name = "name")
    private String companyName;

    @Column(name = "sector")
    private String sector;

    @Column(name = "cap")
    private String marketCap;

    @Column(name = "price")
    private Double price;

    @Column(name = "last_day_price")
    private Double lastDayPrice;

    @Column(name = "optimism")
    private Integer optimism;

    @Column(name = "volatile")
    private Boolean volatileStock;

    public Stock(String ticker, String companyName, String sector, String marketCap, boolean volatileStock){
        this.ticker = ticker;
        this.companyName = companyName;
        this.sector = sector;
        this.marketCap = marketCap;
        this.volatileStock = volatileStock;
        this.price = 0.0;
        this.lastDayPrice = 0.0;
        this.optimism = 0;
    }
}
