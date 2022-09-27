package org.api.stockmarket.stocks.stock.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.api.stockmarket.indexfund.utils.Capitalize;
import org.api.stockmarket.stocks.stock.enums.MarketCap;
import org.api.stockmarket.stocks.stock.exception.StockNotFoundException;
import org.api.stockmarket.stocks.stock.model.entity.Stock;
import org.api.stockmarket.stocks.stock.service.StockService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/stocks")
@AllArgsConstructor
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping(value = "/{ticker}")
    public Stock getIndividualStockData(@PathVariable String ticker) throws StockNotFoundException {
        return stockService.getStockByTickerSymbol(ticker);
    }

    @GetMapping(value = "/all")
    public List<Stock> getAllStockData(){
        return stockService.getAllStocks();
    }

    @GetMapping(value = "/marketCap/{marketCap}")
    public List<Stock> getAllStocksByMarketCap(@PathVariable String marketCap){
        MarketCap cap = MarketCap.valueOf(Capitalize.capitalize(marketCap));
        return stockService.getAllStocksByMarketCap(cap);
    }

    @GetMapping(value = "/sector/{sector}")
    public List<Stock> getAllStocksBySector(@PathVariable String sector){
        return stockService.getAllStocksBySector(sector);
    }

    @GetMapping(value = "/price/{ticker}")
    public double getStockPrice(@PathVariable String ticker) throws StockNotFoundException {
        return stockService.getStockPriceWithTickerSymbol(ticker);
    }

    @GetMapping(value = "/random")
    public Stock getRandomStock() {
        return stockService.getRandomStock();
    }
}