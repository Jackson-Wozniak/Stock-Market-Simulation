package org.api.stockmarket.stocks.stock.controller;

import lombok.AllArgsConstructor;
import org.api.stockmarket.indexfund.utils.Capitalize;
import org.api.stockmarket.stocks.stock.enums.MarketCap;
import org.api.stockmarket.stocks.stock.exception.StockNotFoundException;
import org.api.stockmarket.stocks.stock.model.entity.Stock;
import org.api.stockmarket.stocks.stock.model.entity.StockHistory;
import org.api.stockmarket.stocks.stock.model.payload.StockDto;
import org.api.stockmarket.stocks.stock.service.StockHistoryService;
import org.api.stockmarket.stocks.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/stocks")
@CrossOrigin(value = "http://127.0.0.1:5500")
@AllArgsConstructor
public class StockController {

    @Autowired
    private StockService stockService;
    @Autowired
    private final StockHistoryService stockHistoryService;

    @GetMapping(value = "/{ticker}")
    public Stock getIndividualStockData(@PathVariable String ticker) {
        return new StockDto(stockService.getStockByTickerSymbol(ticker));
    }

    @GetMapping(value = "/all")
    public List<Stock> getAllStockData() {
        return stockService.getAllStocks()
                .stream().map(StockDto::new).collect(Collectors.toList());
    }

    @GetMapping(value = "/marketCap/{marketCap}")
    public List<Stock> getAllStocksByMarketCap(@PathVariable String marketCap) {
        MarketCap cap = MarketCap.valueOf(Capitalize.capitalize(marketCap));
        return stockService.getAllStocksByMarketCap(cap);
    }

    @GetMapping(value = "/sector/{sector}")
    public List<Stock> getAllStocksBySector(@PathVariable String sector) {
        return stockService.getAllStocksBySector(sector);
    }

    @GetMapping(value = "/price/{ticker}")
    public double getStockPrice(@PathVariable String ticker) throws StockNotFoundException {
        return stockService.getStockPriceWithTickerSymbol(ticker);
    }

    @GetMapping(value = "/random")
    public Stock getRandomStock() {
        return new StockDto(stockService.getRandomStock());
    }

    @RequestMapping(value = "/history/{ticker}")
    public List<StockHistory> getStockHistory(@PathVariable String ticker) {
        return stockHistoryService.findStockHistoryByTicker(ticker);
    }
}