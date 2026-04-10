package io.github.stockmarket.market.stocks.service;

import io.github.stockmarket.market.stocks.model.Stock;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StockService {

    private final AtomicInteger ticksSinceSave = new AtomicInteger(0);
    private final AtomicInteger totalStocks = new AtomicInteger(0);
    private final ConcurrentHashMap<Long, Stock> stockCache = new ConcurrentHashMap<>();

    public void runPriceChanges(){
//        if(stockCache.isEmpty() || stockCache.size() != totalStocks.get()){
//            reloadCacheFromDatabase();
//        }
//        stockCache.forEach((key, value) -> value.runPriceChange());
//
//        if(ticksSinceSave.incrementAndGet() > 9){
//            pricingModelRepository.saveAll(stockCache.values().stream()
//                    .map(Stock::getPricingModel).toList());
//            ticksSinceSave.set(0);
//        }
    }
}
