package org.api.stockmarket.modules.stocks.controller;

import lombok.AllArgsConstructor;
import org.api.stockmarket.common.utils.Capitalize;
import org.api.stockmarket.modules.stocks.dto.DetailedStockDTO;
import org.api.stockmarket.modules.stocks.dto.StockDTO;
import org.api.stockmarket.modules.stocks.dto.StockPriceHistoryDTO;
import org.api.stockmarket.modules.stocks.entity.Stock;
import org.api.stockmarket.modules.stocks.enums.MarketCap;
import org.api.stockmarket.modules.stocks.exception.StockNotFoundException;
import org.api.stockmarket.modules.stocks.service.PriceRecordService;
import org.api.stockmarket.modules.stocks.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/stocks")
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://127.0.0.1:5501"})
@AllArgsConstructor
@SuppressWarnings("unused")
public class StockController {

    private StockService stockService;
    private final PriceRecordService priceRecordService;

    @GetMapping(value = "/{ticker}")
    public ResponseEntity<?> getIndividualStockData(
            @PathVariable String ticker,
            @RequestParam(value = "is_detailed", required = false, defaultValue = "f") String isDetailed) {

        Stock stock = stockService.getStockByTickerSymbol(ticker);

        if(isDetailed.equalsIgnoreCase("true") || isDetailed.equalsIgnoreCase("t")){
            return ResponseEntity.ok(new DetailedStockDTO(stockService.getStockByTickerSymbol(ticker)));
        }
        return ResponseEntity.ok(new StockDTO(stockService.getStockByTickerSymbol(ticker)));
    }

    @GetMapping
    public List<StockDTO> getAllStockData() {
        return stockService.getAllStocks().stream()
                .map(StockDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/marketCap/{marketCap}")
    public List<StockDTO> getAllStocksByMarketCap(@PathVariable String marketCap) {
        MarketCap cap = MarketCap.valueOf(Capitalize.capitalize(marketCap));
        return stockService.getAllStocksByMarketCap(cap).stream()
                .map(StockDTO::new)
                .toList();
    }

    @GetMapping(value = "/sector/{sector}")
    public List<StockDTO> getAllStocksBySector(@PathVariable String sector) {
        return stockService.getAllStocksBySector(sector).stream()
                .map(StockDTO::new)
                .toList();
    }

    @GetMapping(value = "/price/{ticker}")
    public double getStockPrice(@PathVariable String ticker) throws StockNotFoundException {
        return stockService.getStockPriceWithTickerSymbol(ticker);
    }

    @GetMapping(value = "/random")
    public DetailedStockDTO getRandomStock() {
        Stock stock = stockService.getRandomStock();
        return new DetailedStockDTO(stock);
    }

    @GetMapping(value = "/history/{ticker}")
    public List<StockPriceHistoryDTO> getStockHistory(@PathVariable String ticker) {
        return priceRecordService.findRecordsByStock(ticker).stream()
                .map(StockPriceHistoryDTO::new)
                .toList();
    }
}