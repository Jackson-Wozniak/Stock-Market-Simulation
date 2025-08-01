package org.api.stockmarket.modules.indexfunds.service;

import lombok.AllArgsConstructor;
import org.api.stockmarket.modules.indexfunds.exception.IndexFundException;
import org.api.stockmarket.modules.indexfunds.model.IndexFund;
import org.api.stockmarket.modules.indexfunds.model.subclass.MarketCapIndexFund;
import org.api.stockmarket.modules.indexfunds.model.subclass.SectorIndexFund;
import org.api.stockmarket.modules.indexfunds.model.subclass.TotalMarketIndexFund;
import org.api.stockmarket.modules.stocks.entity.Stock;
import org.api.stockmarket.modules.stocks.enums.MarketCap;
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
            if(!allMarketCaps.containsKey(stock.getCompany().getMarketCap())){
                allMarketCaps.put(stock.getCompany().getMarketCap(), new ArrayList<>());
            }
            allMarketCaps.get(stock.getCompany().getMarketCap()).add(stock);
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
            if(!allSectors.containsKey(stock.getCompany().getSector())){
                allSectors.put(stock.getCompany().getSector(), new ArrayList<>());
            }
            allSectors.get(stock.getCompany().getSector()).add(stock);
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
}
