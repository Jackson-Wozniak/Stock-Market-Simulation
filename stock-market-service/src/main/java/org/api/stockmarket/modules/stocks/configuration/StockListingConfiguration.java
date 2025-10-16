package org.api.stockmarket.modules.stocks.configuration;

import lombok.AllArgsConstructor;
import org.api.stockmarket.core.csv.CSVReader;
import org.api.stockmarket.modules.stocks.csv.StockCSVObject;
import org.api.stockmarket.modules.stocks.entity.Stock;
import org.api.stockmarket.modules.stocks.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.DependsOn;

@Configuration
@AllArgsConstructor
@DependsOn({"marketConfiguration"})
public class StockListingConfiguration {

    private final StockService stockService;
    private final CSVReader<StockCSVObject, Stock> stockCSVReader;

    private final Logger logger = LoggerFactory.getLogger(StockListingConfiguration.class);

    @PostConstruct
    public void saveStocksToDatabaseOnStartup() {
        if (stockCSVReader.lineCount() != stockService.findStockRowCount()) {
            logger.info("Saving Default Stocks");
            stockService.saveDefaultStockToDatabase(stockCSVReader.toEntities());
        }
    }
}
