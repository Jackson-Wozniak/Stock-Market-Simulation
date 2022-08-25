package stocktradingsimulator.stock;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/stocks")
@AllArgsConstructor
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping(value = "/stock/{ticker}")
    public Stock getIndividualStockData(@PathVariable String ticker){
        try{
            return stockService.getStockByTickerSymbol(ticker);
        }catch(StockNotFoundException ex){
            return null;
        }
    }

    @GetMapping(value = "/all")
    public List<Stock> getAllStockData(){
        return stockService.getAllStocks();
    }

    @GetMapping(value = "/marketCap/{marketCap}")
    public List<Stock> getAllStocksByMarketCap(@PathVariable String marketCap){
        return stockService.getAllStocksByMarketCap(marketCap);
    }

    @GetMapping(value = "/sector/{sector}")
    public List<Stock> getAllStocksBySector(@PathVariable String sector){
        return stockService.getAllStocksBySector(sector);
    }

    @GetMapping(value = "/price/{ticker}")
    public double getStockPrice(@PathVariable String ticker){
        try{
            return stockService.getStockPriceWithTickerSymbol(ticker);
        }catch(StockNotFoundException ex){
            return 0.0;
        }
    }
}
