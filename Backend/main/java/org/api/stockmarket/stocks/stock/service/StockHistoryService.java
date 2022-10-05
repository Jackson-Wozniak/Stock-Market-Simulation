package org.api.stockmarket.stocks.stock.service;

import lombok.AllArgsConstructor;
import org.api.stockmarket.market.entity.Market;
import org.api.stockmarket.market.service.MarketService;
import org.api.stockmarket.stocks.stock.model.entity.StockHistory;
import org.api.stockmarket.stocks.stock.repository.StockHistoryRepository;
import org.api.stockmarket.stocks.stock.utils.SortHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StockHistoryService {

    @Autowired
    private final StockHistoryRepository stockHistoryRepository;
    @Autowired
    private final StockService stockService;
    @Autowired
    private final MarketService marketService;

    public void saveStockHistoryDaily() {
        Market market = marketService.findMarketEntity();
        stockService.getAllStocks().forEach(stock -> {
            stockHistoryRepository.save(new StockHistory(
                    market.getDate(),
                    stock.getTicker(),
                    stock.getPrice()));
        });
    }

    public List<StockHistory> findStockHistoryByTicker(String ticker) {
        List<StockHistory> stockHistory = stockHistoryRepository.findAll().stream()
                .filter(history -> history.getTicker().equalsIgnoreCase(ticker))
                .collect(Collectors.toList());
        SortHistory.sortStockHistoryByDate(stockHistory);
        return stockHistory;
    }
}
