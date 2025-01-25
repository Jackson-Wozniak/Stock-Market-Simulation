package org.api.stockmarket.stocks.stock.utils;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.api.stockmarket.indexfund.exception.IndexFundException;
import org.api.stockmarket.stocks.stock.entity.Stock;
import org.api.stockmarket.stocks.stock.enums.InvestorRating;
import org.api.stockmarket.stocks.stock.enums.MarketCap;
import org.api.stockmarket.stocks.stock.enums.Volatility;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@NoArgsConstructor
public class CsvReader {

    private String stocksPath = "text/stocks.csv";

    //for testing with different csv file
    public CsvReader(String path){
        this.stocksPath = path;
    }

    public List<Stock> readAllStocks() throws IOException {
        ClassPathResource resource = new ClassPathResource(stocksPath);
        InputStreamReader streamReader = new InputStreamReader(resource.getInputStream());

        List<String[]> allLines = toArray(new BufferedReader(streamReader).lines().toList());
        List<Stock> stocks = allLines.stream().map(this::mapStocksFromLine).toList();
        if(stocks.stream().anyMatch(Objects::isNull)) throw new IOException("Data not clean");

        return stocks;
    }

    private List<String[]> toArray(List<String> strings){
        return strings.stream().map(str -> str.split(",")).toList();
    }

    private Stock mapStocksFromLine(String[] line){
        MarketCap cap = MarketCap.map(line[2]);
        if(cap == null || !enumChecks(line)) return null;

        return switch (cap){
            case Large -> Stock.largeCap(
                    line[0], line[1], line[3], Volatility.map(line[4]), InvestorRating.map(line[5]));
            case Mid -> Stock.midCap(
                    line[0], line[1], line[3], Volatility.map(line[4]), InvestorRating.map(line[5]));
            case Small -> Stock.smallCap(
                    line[0], line[1], line[3], Volatility.map(line[4]), InvestorRating.map(line[5]));
        };

    }

    private boolean enumChecks(String[] line) {
        return Volatility.map(line[4]) != null && InvestorRating.map(line[5]) != null;
    }
}
