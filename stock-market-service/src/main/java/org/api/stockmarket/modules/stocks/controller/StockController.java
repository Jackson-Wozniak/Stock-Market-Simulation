package org.api.stockmarket.modules.stocks.controller;

import lombok.AllArgsConstructor;
import org.api.stockmarket.core.utils.Capitalize;
import org.api.stockmarket.modules.stocks.dto.PriceRecordDTO;
import org.api.stockmarket.modules.stocks.dto.StockBasicDTO;
import org.api.stockmarket.modules.stocks.dto.StockDTOMapper;
import org.api.stockmarket.modules.stocks.entity.Stock;
import org.api.stockmarket.modules.stocks.enums.MarketCap;
import org.api.stockmarket.modules.stocks.enums.StockViewType;
import org.api.stockmarket.modules.stocks.exception.StockNotFoundException;
import org.api.stockmarket.modules.stocks.service.PriceRecordService;
import org.api.stockmarket.modules.stocks.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/stocks")
@CrossOrigin(origins = "*")
@AllArgsConstructor
@SuppressWarnings("unused")
public class StockController {

    private StockService stockService;
    private final PriceRecordService priceRecordService;

    @GetMapping(value = "/{ticker}")
    public ResponseEntity<?> getIndividualStockData(
            @PathVariable String ticker,
            @RequestParam(defaultValue = "basic") StockViewType view) {
        Stock stock = stockService.getStockByTickerSymbol(ticker);
        return ResponseEntity.ok(StockDTOMapper.map(stock, view));
    }

    @GetMapping
    public List<StockBasicDTO> getAllStockData() {
        return StockDTOMapper.map(stockService.getAllStocks(), StockViewType.BASIC);
    }

    @GetMapping(value = "/marketCap/{marketCap}")
    public List<StockBasicDTO> getAllStocksByMarketCap(@PathVariable String marketCap) {
        MarketCap cap = MarketCap.valueOf(Capitalize.capitalize(marketCap));
        return StockDTOMapper.map(stockService.getAllStocksByMarketCap(cap), StockViewType.BASIC);
    }

    @GetMapping(value = "/sector/{sector}")
    public List<StockBasicDTO> getAllStocksBySector(@PathVariable String sector) {
        return StockDTOMapper.map(stockService.getAllStocksBySector(sector), StockViewType.BASIC);
    }

    @GetMapping(value = "/price/{ticker}")
    public double getStockPrice(@PathVariable String ticker) throws StockNotFoundException {
        return stockService.getStockPriceWithTickerSymbol(ticker);
    }

    @GetMapping(value = "/random")
    public StockBasicDTO getRandomStock() {
        Stock stock = stockService.getRandomStock();
        return new StockBasicDTO(stock);
    }
}