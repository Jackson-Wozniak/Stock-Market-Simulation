package stocktradingsimulator.market.scheduled;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import stocktradingsimulator.market.entity.Market;
import stocktradingsimulator.stock.defaults.DefaultNewsEvents;
import stocktradingsimulator.stock.model.entity.Stock;
import stocktradingsimulator.stock.service.NewsService;
import stocktradingsimulator.stock.service.StockService;

@Component
@AllArgsConstructor
public class RandomNewsEvents {

    @Autowired
    private final NewsService newsService;
    @Autowired
    private final StockService stockService;

    public void processPositiveNewsEvent(String date){
        Stock stock = stockService.getRandomStock();
        stock.setPrice(stock.getPrice() * 1.1);
        stockService.updateStockInDatabase(stock);

        newsService.saveNewsForStock(stock, DefaultNewsEvents.positiveNewsEvents(stock), date);
    }

    public void processNegativeNewsEvents(String date){
        Stock stock = stockService.getRandomStock();
        stock.setPrice(stock.getPrice() * .9);
        stockService.updateStockInDatabase(stock);

        newsService.saveNewsForStock(stock, DefaultNewsEvents.negativeNewsEvents(stock), date);
    }
}
