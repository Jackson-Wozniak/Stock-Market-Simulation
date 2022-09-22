package stocktradingsimulator.stock.model.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import stocktradingsimulator.stock.model.entity.News;
import stocktradingsimulator.stock.model.entity.Stock;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StockDto {

    private String ticker;
    private String companyName;
    private String sector;
    private String marketCap;
    private Double price;
    private Double lastDayPrice;
    private Integer optimism;
    private Boolean volatileStock;
    private List<News> newsHistory;

    public StockDto(Stock stock){
        this.ticker = stock.getTicker();
        this.companyName = stock.getCompanyName();
        this.sector = stock.getSector();
        this.marketCap = stock.getMarketCap();
        this.price = stock.getPrice();
        this.lastDayPrice = stock.getLastDayPrice();
        this.optimism = stock.getOptimism();
        this.volatileStock = stock.getVolatileStock();
        this.newsHistory = stock.getNewsHistory();
    }
}
