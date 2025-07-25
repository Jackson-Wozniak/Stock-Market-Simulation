package org.api.stockmarket.modules.indexfunds.service;

import lombok.AllArgsConstructor;
import org.api.stockmarket.modules.indexfunds.exception.IndexFundException;
import org.api.stockmarket.modules.indexfunds.model.IndexFund;
import org.api.stockmarket.modules.indexfunds.model.subclass.MarketCapIndexFund;
import org.api.stockmarket.modules.indexfunds.model.subclass.SectorIndexFund;
import org.api.stockmarket.modules.indexfunds.model.subclass.TotalMarketIndexFund;
import org.api.stockmarket.modules.indexfunds.model.subclass.VolatilityIndexFund;
import org.api.stockmarket.modules.stocks.entity.Stock;
import org.api.stockmarket.modules.stocks.enums.MarketCap;
import org.api.stockmarket.modules.stocks.enums.Volatility;
import org.api.stockmarket.modules.stocks.service.StockService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class IndexFundService {

    private final StockService stockService;

    public List<IndexFund> findAllIndexFunds(){
        List<IndexFund> funds = new ArrayList<>();
        funds.add(findTotalMarketFund());
        funds.addAll(findMarketCapFunds());
        funds.addAll(findSectorFunds());
        funds.addAll(findVolatilityFunds());
        return funds;
    }

    public IndexFund findTotalMarketFund(){
        double average = stockService.getAllStocks().stream()
                .mapToDouble(Stock::getPrice)
                .average().orElse(0.0);
        return new TotalMarketIndexFund(average);
    }

    public List<IndexFund> findMarketCapFunds(){
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

    public IndexFund findMarketCapFunds(MarketCap marketCap){
        double average = stockService.getAllStocksByMarketCap(marketCap).stream()
                .mapToDouble(Stock::getPrice)
                .average().orElse(0.0);
        return new MarketCapIndexFund(marketCap, average);
    }

    public List<IndexFund> findSectorFunds(){
        Map<String, ArrayList<Stock>> allSectors = new HashMap<>();
        stockService.getAllStocks().forEach(stock -> {
            if(!allSectors.containsKey(stock.getSector())){
                allSectors.put(stock.getSector(), new ArrayList<>());
            }
            allSectors.get(stock.getSector()).add(stock);
        });
        return allSectors.entrySet().stream()
                .map((entry) -> {
                    double sum = entry.getValue().stream()
                            .mapToDouble(Stock::getPrice).sum();
                    double average = sum / entry.getValue().size();
                    return (IndexFund) new SectorIndexFund(entry.getKey(), average);
                }).toList();
    }

    public IndexFund findSectorFunds(String sector){
        List<Stock> sectorStocks = stockService.getAllStocksBySector(sector);
        if(sectorStocks.isEmpty()) throw IndexFundException.invalidSector(sector);

        double average = sectorStocks.stream()
                .mapToDouble(Stock::getPrice)
                .average().orElse(0.0);
        return new SectorIndexFund(sector, average);
    }

    public List<IndexFund> findVolatilityFunds(){
        Map<Volatility, ArrayList<Stock>> allVolatilities = new HashMap<>();
        stockService.getAllStocks().forEach(stock -> {
            if(!allVolatilities.containsKey(stock.getVolatileStock())){
                allVolatilities.put(stock.getVolatileStock(), new ArrayList<>());
            }
            allVolatilities.get(stock.getVolatileStock()).add(stock);
        });
        return allVolatilities.entrySet().stream()
                .map((entry) -> {
                    double sum = entry.getValue().stream()
                            .mapToDouble(Stock::getPrice).sum();
                    double average = sum / entry.getValue().size();
                    return (IndexFund) new VolatilityIndexFund(entry.getKey(), average);
                }).toList();
    }

    public IndexFund findVolatilityFunds(Volatility volatility){
        double average = stockService.getAllStocksByVolatility(volatility).stream()
                .mapToDouble(Stock::getPrice)
                .average().orElse(0.0);
        return new VolatilityIndexFund(volatility, average);
    }
}
