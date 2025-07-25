package org.api.stockmarket.stocks.stock.configuration;

import lombok.AllArgsConstructor;
import org.api.stockmarket.stocks.stock.properties.DefaultStocksList;
import org.api.stockmarket.stocks.stock.service.StockService;
import org.api.stockmarket.stocks.stock.utils.CsvReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

import java.io.IOException;

@Configuration
@AllArgsConstructor
public class StockConfiguration {

    private final StockService stockService;
    private final CsvReader csvReader;
    private static final boolean IS_CSV_FULL = true;

    private final Logger logger = LoggerFactory.getLogger(StockConfiguration.class);

    @PostConstruct
    public void saveStocksToDatabaseOnStartup() throws IOException {
        /*
            CSV is not filled yet with stock info so use the original hard-coded defaults
        */
        if(!IS_CSV_FULL){
            if (DefaultStocksList.getCountForDefaultStocks() != stockService.findStockRowCount()) {
                logger.info("Saving Default Stocks");
                stockService.saveDefaultStockToDatabase(DefaultStocksList.getAllDefaultStocks());
            }
            return;
        }

        if (csvReader.count() != stockService.findStockRowCount()) {
            logger.info("Saving Default Stocks");
            stockService.saveDefaultStockToDatabase(csvReader.readAllStocks());
        }
    }
}
