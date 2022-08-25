package stocktradingsimulator.stock;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public List<Stock> getAllStocks(){
        return stockRepository.findAll();
    }

    public List<Stock> getAllStocksByMarketCap(String marketCap){
        return stockRepository.findAll().stream()
                .filter(stock -> stock.getMarketCap()
                        .equals(marketCap)).collect(Collectors.toList());
    }

    public List<Stock> getAllStocksBySector(String sector){
        return stockRepository.findAll().stream()
                .filter(stock -> stock.getSector()
                        .equals(sector)).collect(Collectors.toList());
    }

    public Stock getStockByTickerSymbol(String ticker) throws StockNotFoundException {
        return stockRepository.findById(ticker)
                .orElseThrow(() -> new StockNotFoundException("No Stock Exists with that Ticker symbol"));
    }

    public double getStockPriceWithTickerSymbol(String ticker) throws StockNotFoundException {
        return stockRepository.findById(ticker).orElseThrow(() ->
                new StockNotFoundException("No Stock Exists with that Ticker symbol"))
                .getPrice();
    }

    public void updateStockInDatabase(Stock stock){
        stockRepository.save(stock);
    }

    public void saveDefaultStockToDatabase(List<Stock> defaultStocks){
        defaultStocks.forEach(defaultStock -> stockRepository.save(defaultStock));
    }
}
