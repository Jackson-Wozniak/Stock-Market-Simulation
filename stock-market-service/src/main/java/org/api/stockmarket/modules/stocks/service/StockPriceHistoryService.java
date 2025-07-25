package org.api.stockmarket.modules.stocks.service;

import lombok.AllArgsConstructor;
import org.api.stockmarket.engine.entity.Market;
import org.api.stockmarket.engine.service.MarketService;
import org.api.stockmarket.modules.stocks.entity.StockPriceHistory;
import org.api.stockmarket.modules.stocks.entity.idclass.StockPriceHistoryId;
import org.api.stockmarket.modules.stocks.repository.StockPriceHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StockPriceHistoryService {

    @Autowired
    private final StockPriceHistoryRepository stockPriceHistoryRepository;
    @Autowired
    private final StockService stockService;
    @Autowired
    private final MarketService marketService;

    public void saveStockHistoryDaily() {
        Market market = marketService.findMarketEntity();
        stockService.getAllStocks().forEach(stock ->
                stockPriceHistoryRepository.save(new StockPriceHistory(
                        new StockPriceHistoryId(market.getDate(), stock.getTicker()),
                        stock,
                        stock.getPrice())));
    }

    public List<StockPriceHistory> findStockHistoryByTicker(String ticker) {
        return stockPriceHistoryRepository.findAll().stream()
                .filter(history -> history.getStock().getTicker().equalsIgnoreCase(ticker))
                .sorted(Comparator.comparing(history -> history.getId().getMarketDate()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void truncateStockHistoryAtEndOfYear() {
        stockPriceHistoryRepository.truncateTable();
    }
}
