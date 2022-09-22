package stocktradingsimulator.market.scheduled;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import stocktradingsimulator.market.entity.Market;
import stocktradingsimulator.market.service.MarketService;
import stocktradingsimulator.market.utils.GetRandomNumber;
import stocktradingsimulator.market.utils.MarketTrajectoryUtils;
import stocktradingsimulator.stock.model.entity.Stock;
import stocktradingsimulator.stock.service.StockService;

import java.util.List;

@Component
@AllArgsConstructor
public class HandleMarketActivity {

    @Autowired
    private final StockService stockService;
    @Autowired
    private final ChangeStockPrices changeStockPrices;
    @Autowired
    private final MarketService marketService;
    @Autowired
    private final RandomNewsEvents randomNewsEvents;

    public void updateNewStockPrices(boolean endOfDay){
        List<Stock> stocks = stockService.getAllStocks();
        stocks.forEach(stock -> {
            if(stock.getPrice() < 1){
                randomNewsEvents.stockBankruptNews(stock, marketService.findMarketEntity().getDate());
                return;
            }
            stock.setPrice(changeStockPrices.automaticPriceChange(stock));
            if(endOfDay){
                stock.setOptimism(updateOptimism(stock));
                stock.setLastDayPrice(stock.getPrice());
            }
            stockService.updateStockInDatabase(stock);
        });
    }

    public String incrementMarketDay(){
        Market market = marketService.findMarketEntity();
        market.incrementDay();
        marketService.saveMarketEntity(market);
        return market.getDate();
    }

    public void updateMarketMonthlyValues(){
        Market market = marketService.findMarketEntity();
        market.setMarketTrajectory(MarketTrajectoryUtils.getNewMarketTrajectory(
                market, stockService.getAllStocks()));
        market.setLastMonthAveragePrice(MarketTrajectoryUtils.stockPricesAverage(
                stockService.getAllStocks()));
        marketService.saveMarketEntity(market);
    }

    public void createRandomNewsEvents(){
        if(GetRandomNumber.drawRandomNumberToThirty() == 10){
            randomNewsEvents.processPositiveNewsEvent(marketService.findMarketEntity().getDate());
            System.out.println("Positive News");
        }else if(GetRandomNumber.drawRandomNumberToThirty() == 20){
            randomNewsEvents.processNegativeNewsEvents(marketService.findMarketEntity().getDate());
            System.out.println("Negative News");
        }
    }

    private int updateOptimism(Stock stock){
        if(stock.getPrice() > stock.getLastDayPrice()){
            return 1;
        }
        if(stock.getPrice() < stock.getLastDayPrice()){
            return -1;
        }
        return 0;
    }
}
