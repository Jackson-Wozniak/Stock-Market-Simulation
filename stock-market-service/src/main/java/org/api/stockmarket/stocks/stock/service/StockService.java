package org.api.stockmarket.stocks.stock.service;

import lombok.AllArgsConstructor;
import org.api.stockmarket.stocks.stock.enums.MarketCap;
import org.api.stockmarket.stocks.stock.enums.Volatility;
import org.api.stockmarket.stocks.stock.exception.StockNotFoundException;
import org.api.stockmarket.stocks.stock.entity.Stock;
import org.api.stockmarket.stocks.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StockService {

    @Autowired
    private final StockRepository stockRepository;

    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    //this method is used to generate random news events
    public Stock getRandomStock() {
        List<Stock> stocks = getAllStocks();
        Collections.shuffle(stocks);
        return stocks.get(0);
    }

    public List<Stock> getAllStocksByMarketCap(MarketCap marketCap) {
        return stockRepository.findAll().stream()
                .filter(stock -> stock.getMarketCap()
                        .equals(marketCap)).collect(Collectors.toList());
    }

    public List<Stock> getAllStocksBySector(String sector) {
        return stockRepository.findAll().stream()
                .filter(stock -> stock.getSector()
                        .equalsIgnoreCase(sector)).collect(Collectors.toList());
    }

    public List<Stock> getAllStocksByVolatility(Volatility volatility) {
        return stockRepository.findAll().stream()
                .filter(stock -> stock.getVolatileStock() .equals(volatility))
                .collect(Collectors.toList());
    }

    public Stock getStockByTickerSymbol(String ticker) {
        return stockRepository.findById(ticker.toUpperCase())
                .orElseThrow(() -> new StockNotFoundException(
                        "No stock with ticker symbol " + ticker + " exists"));
    }

    public double getStockPriceWithTickerSymbol(String ticker) {
        if (stockTickerExists(ticker)) {
            throw new StockNotFoundException("No stock with ticker symbol " + ticker + " exists");
        }
        return getStockByTickerSymbol(ticker).getPrice();
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

    public void saveDefaultStockToDatabase(List<Stock> defaultStocks) {
        List<Stock> currentStocks = stockRepository.findAll();
        List<Stock> unsavedStocks = new ArrayList<>();
        defaultStocks.forEach(stock -> {
            if (!stockTickerExistsInList(currentStocks, stock.getTicker())) {
                unsavedStocks.add(stock);
            }
        });
        stockRepository.saveAll(unsavedStocks);
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
