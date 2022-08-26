package stocktradingsimulator.market;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import stocktradingsimulator.stock.Stock;
import stocktradingsimulator.stock.StockService;

import java.util.List;

@Component
@AllArgsConstructor
public class HandleMarketActivity {

    @Autowired
    private StockService stockService;

    public void printPrices(){
        List<Stock> stocks = stockService.getAllStocks();
        stocks.forEach(stock -> {
            System.out.println(ChangeStockPrices.automaticPriceChange(stock));
        });
    }
}
