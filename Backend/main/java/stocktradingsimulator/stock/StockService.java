package stocktradingsimulator.stock;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stocktradingsimulator.stock.utils.DoesStockExist;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StockService {

    @Autowired
    private final StockRepository stockRepository;

    public List<Stock> getAllStocks(){
        return stockRepository.findAll();
    }

    public List<Stock> getAllStocksByMarketCap(String marketCap){
        return stockRepository.findAll().stream()
                .filter(stock -> stock.getMarketCap()
                        .equalsIgnoreCase(marketCap)).collect(Collectors.toList());
    }

    public List<Stock> getAllStocksBySector(String sector){
        return stockRepository.findAll().stream()
                .filter(stock -> stock.getSector()
                        .equalsIgnoreCase(sector)).collect(Collectors.toList());
    }

    public Stock getStockByTickerSymbol(String ticker) throws StockNotFoundException {
        return stockRepository.findById(ticker)
                .orElseThrow(() -> new StockNotFoundException(
                        "No stock with ticker symbol " + ticker + " exists"));
    }

    public double getStockPriceWithTickerSymbol(String ticker) throws StockNotFoundException {
        if(!DoesStockExist.stockExistsWithTicker(this, ticker)){
            throw new StockNotFoundException("No stock with ticker symbol " + ticker + " exists");
        }
        return getStockByTickerSymbol(ticker).getPrice();
    }

    //Ignore any stocks that do not currently exist
    public void updateStockInDatabase(Stock stock) {
        if(!DoesStockExist.stockExistsWithTicker(this, stock.getTicker())){
            return;
        }
        stockRepository.save(stock);
    }

    public void saveDefaultStockToDatabase(List<DefaultStock> defaultStocks){
        defaultStocks.forEach(stock -> stockRepository.save((Stock) stock));
    }
}
