package stocktradingsimulator.stocks.stock.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stocktradingsimulator.stocks.stock.enums.MarketCap;
import stocktradingsimulator.stocks.stock.exception.StockNotFoundException;
import stocktradingsimulator.stocks.stock.model.object.DefaultStock;
import stocktradingsimulator.stocks.stock.model.entity.Stock;
import stocktradingsimulator.stocks.stock.repository.StockRepository;
import stocktradingsimulator.stocks.stock.utils.DoesStockExist;

import java.util.Collections;
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

    //this method is used to generate random news events
    public Stock getRandomStock(){
        List<Stock> stocks = getAllStocks();
        Collections.shuffle(stocks);
        return stocks.get(0);
    }

    public List<Stock> getAllStocksByMarketCap(MarketCap marketCap){
        return stockRepository.findAll().stream()
                .filter(stock -> stock.getMarketCap()
                        .equals(marketCap)).collect(Collectors.toList());
    }

    public List<Stock> getAllStocksBySector(String sector){
        return stockRepository.findAll().stream()
                .filter(stock -> stock.getSector()
                        .equalsIgnoreCase(sector)).collect(Collectors.toList());
    }

    public List<Stock> getAllStocksByVolatility(boolean volatility){
        return stockRepository.findAll().stream()
                .filter(stock -> stock.getVolatileStock() == volatility)
                .collect(Collectors.toList());
    }

    public Stock getStockByTickerSymbol(String ticker) throws StockNotFoundException {
        return stockRepository.findById(ticker.toUpperCase())
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

    public int findStockRowCount(){
        return (int) stockRepository.count();
    }

    public void saveDefaultStockToDatabase(List<DefaultStock> defaultStocks){
        defaultStocks.forEach(stock -> {
            if(DoesStockExist.stockExistsWithTicker(this, stock.getTicker())){
                return;
            }
            System.out.println(stock.getTicker() + " saved");
            stockRepository.save((Stock) stock);
        });
    }
}
