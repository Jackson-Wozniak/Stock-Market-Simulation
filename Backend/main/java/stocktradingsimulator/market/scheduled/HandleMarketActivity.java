package stocktradingsimulator.market.scheduled;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.stereotype.Component;
import stocktradingsimulator.market.entity.Market;
import stocktradingsimulator.market.scheduled.ChangeStockPrices;
import stocktradingsimulator.market.service.MarketService;
import stocktradingsimulator.market.utils.MarketTrajectoryUtils;
import stocktradingsimulator.stock.Stock;
import stocktradingsimulator.stock.StockService;

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

    public void updateNewStockPrices(boolean endOfDay){
        List<Stock> stocks = stockService.getAllStocks();
        stocks.forEach(stock -> {
            stock.setPrice(changeStockPrices.automaticPriceChange(stock));
            if(endOfDay){
                stock.setOptimism(updateOptimism(stock));
                stock.setLastDayPrice(stock.getPrice());
            }
            stockService.updateStockInDatabase(stock);
        });
    }

    public void updateMarketMonthlyValues(){
        Market market = marketService.findMarketEntity();
        market.setMarketTrajectory(MarketTrajectoryUtils.getNewMarketTrajectory(
                market, stockService.getAllStocks()));
        market.setLastMonthAveragePrice(MarketTrajectoryUtils.stockPricesAverage(
                stockService.getAllStocks()));
        marketService.saveMarketEntity(market);
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