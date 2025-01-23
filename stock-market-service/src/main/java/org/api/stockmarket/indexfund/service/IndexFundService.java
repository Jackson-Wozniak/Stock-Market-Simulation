package org.api.stockmarket.indexfund.service;

import lombok.AllArgsConstructor;
import org.api.stockmarket.indexfund.model.IndexFund;
import org.api.stockmarket.indexfund.model.subclass.MarketCapIndexFund;
import org.api.stockmarket.stocks.stock.entity.Stock;
import org.api.stockmarket.stocks.stock.enums.MarketCap;
import org.api.stockmarket.stocks.stock.service.StockService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class IndexFundService {

    private final StockService stockService;

    public List<IndexFund> marketCapFunds(){
        Map<MarketCap, ArrayList<Stock>> allMarketCaps = new HashMap<>();
        stockService.getAllStocks().forEach(stock -> {
            if(!allMarketCaps.containsKey(stock.getMarketCap())){
                allMarketCaps.put(stock.getMarketCap(), new ArrayList<>());
            }
            allMarketCaps.get(stock.getMarketCap()).add(stock);
        });
        return allMarketCaps.entrySet().stream()
                .map((entry) -> {
                    double sum = entry.getValue().stream()
                            .mapToDouble(Stock::getPrice).sum();
                    double average = sum / entry.getValue().size();
                    return (IndexFund) new MarketCapIndexFund(entry.getKey(), average);
                }).toList();
    }
}
