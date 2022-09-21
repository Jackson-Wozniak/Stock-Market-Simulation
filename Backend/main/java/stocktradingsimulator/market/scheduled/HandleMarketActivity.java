package stocktradingsimulator.market.scheduled;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import stocktradingsimulator.market.scheduled.ChangeStockPrices;
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

    public int updateOptimism(Stock stock){
        if(stock.getPrice() > stock.getLastDayPrice()){
            return 1;
        }
        if(stock.getPrice() < stock.getLastDayPrice()){
            return -1;
        }
        return 0;
    }
}
