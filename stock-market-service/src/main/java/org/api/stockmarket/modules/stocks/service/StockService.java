package org.api.stockmarket.modules.stocks.service;

import org.api.stockmarket.modules.news.entity.NewsRelease;
import org.api.stockmarket.modules.stocks.entity.Stock;
import org.api.stockmarket.modules.stocks.enums.MarketCap;
import org.api.stockmarket.modules.stocks.exception.StockNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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
