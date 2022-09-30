package org.api.stockmarket.stocks.stock.service;

import org.api.stockmarket.market.entity.Market;
import org.api.stockmarket.market.service.MarketService;
import org.api.stockmarket.stocks.stock.model.entity.StockHistory;
import org.api.stockmarket.stocks.stock.repository.StockHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockHistoryService {

    @Autowired
    private StockHistoryRepository stockHistoryRepository;
    @Autowired
    private StockService stockService;
    @Autowired
    private MarketService marketService;

    public void saveStockHistoryDaily(){
        Market market = marketService.findMarketEntity();
        stockService.getAllStocks().forEach(stock -> {
            stockHistoryRepository.save(new StockHistory(
                    market.getDate(),
                    stock.getTicker(),
                    stock.getPrice()));
        });
    }

    public List<StockHistory> findStockHistory(String ticker){
        return stockHistoryRepository.findAll().stream()
                .filter(stockHistory -> stockHistory.getStockTicker().equalsIgnoreCase(ticker))
                .collect(Collectors.toList());
    }
}