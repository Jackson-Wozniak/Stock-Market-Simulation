package org.api.stockmarket.modules.stocks.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.api.stockmarket.modules.stocks.entity.PricingModel;
import org.api.stockmarket.modules.stocks.entity.Stock;
import org.api.stockmarket.modules.stocks.enums.MarketCap;
import org.api.stockmarket.modules.stocks.exception.StockNotFoundException;
import org.api.stockmarket.modules.stocks.repository.PricingModelRepository;
import org.api.stockmarket.modules.stocks.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class StockService {

    private final StockRepository stockRepository;
    private final AtomicInteger ticksSinceSave = new AtomicInteger(0);
    private final AtomicInteger totalStocks = new AtomicInteger(0);
    private final PricingModelRepository pricingModelRepository;
    private final ConcurrentHashMap<Long, Stock> stockCache = new ConcurrentHashMap<>();

    public StockService(StockRepository s, PricingModelRepository p){
        this.stockRepository = s;
        this.pricingModelRepository = p;
        List<Stock> stocks = stockRepository.findAll();
        stockCache.clear();
        stockCache.putAll(stocks.stream().collect(Collectors.toMap(Stock::getId,st -> st)));
        totalStocks.set(stocks.size());
    }

    public List<Stock> getAllStocks() {
        return new ArrayList<>(stockCache.values());
    }

    //this method is used to generate random news events
    public Stock getRandomStock() {
        List<Stock> stocks = getAllStocks();
        Collections.shuffle(stocks);
        return stocks.get(0);
    }

    public void runPriceChanges(){
        if(stockCache.isEmpty() || stockCache.size() != totalStocks.get()){
            List<Stock> stocks = stockRepository.findAll();
            stockCache.clear();
            stockCache.putAll(stocks.stream().collect(Collectors.toMap(Stock::getId,s -> s)));
            totalStocks.set(stocks.size());
        }
        stockCache.forEach((key, value) -> value.runPriceChange());

        if(ticksSinceSave.incrementAndGet() > 9){
            pricingModelRepository.saveAll(stockCache.values().stream()
                    .map(Stock::getPricingModel).toList());
            ticksSinceSave.set(0);
        }
    }

    public List<Stock> getAllStocksByMarketCap(MarketCap marketCap) {
        return getAllStocks().stream()
                .filter(stock -> stock.getCompany().getMarketCap()
                        .equals(marketCap)).collect(Collectors.toList());
    }

    public List<Stock> getAllStocksBySector(String sector) {
        return getAllStocks().stream()
                .filter(stock -> stock.getCompany().getSector()
                        .equalsIgnoreCase(sector)).collect(Collectors.toList());
    }

    public Stock getStockByTickerSymbol(String ticker) {
        return stockCache.getOrDefault(1L, null);
    }

    public double getStockPriceWithTickerSymbol(String ticker) {
        if (stockTickerExists(ticker)) {
            throw new StockNotFoundException("No stock with ticker symbol " + ticker + " exists");
        }
        return getStockByTickerSymbol(ticker).getPricingModel().getPrice().doubleValue();
    }

    //Ignore any stocks that do not currently exist
    public void updateStockInDatabase(Stock stock) {
        if (stockTickerExists(stock.getTicker())) {
            return;
        }
        stockRepository.save(stock);
    }

    //Ignore any stocks that do not currently exist
    public void updateAllStocksInDatabase(List<Stock> stocks) {
        stockRepository.saveAll(stocks);
    }

    public int findStockRowCount() {
        return (int) stockRepository.count();
    }

    /*
    This method does NOT override or delete any stocks. We only add stocks that do not
    show up in the database already. A separate method may be needed if we want to delete/modify stocks
    upon startup.
     */
    public void saveDefaultStockToDatabase(List<Stock> defaultStocks) {
        stockRepository.saveAll(defaultStocks);
        List<Stock> stocks = stockRepository.findAll();
        stockCache.clear();
        stockCache.putAll(stocks.stream().collect(Collectors.toMap(Stock::getId,st -> st)));
        totalStocks.set(stocks.size());
    }

    //Used for searching which default stocks do not exist and should be saved on startup
    public static boolean stockTickerExistsInList(List<Stock> stocks, String ticker) {
        return stocks.stream()
                .map(Stock::getTicker)
                .anyMatch(stockTicker -> stockTicker.equals(ticker));
    }

    public boolean stockTickerExists(String ticker){
        return stockRepository.findById(ticker).isPresent();
    }
}
