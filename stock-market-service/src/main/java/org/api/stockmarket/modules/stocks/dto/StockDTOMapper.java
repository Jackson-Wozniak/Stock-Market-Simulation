package org.api.stockmarket.modules.stocks.dto;

import org.api.stockmarket.modules.stocks.entity.Stock;
import org.api.stockmarket.modules.stocks.enums.StockViewType;

import java.util.List;

public class StockDTOMapper {
    public static StockBasicDTO map(Stock stock, StockViewType view){
        return switch (view){
            case BASIC -> new StockBasicDTO(stock);
            case DETAILED -> new StockDetailedDTO(stock);
            case FULL -> new StockFullDTO(stock);
        };
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> map(List<Stock> stocks, StockViewType view) {
        return (List<T>) switch (view) {
            case BASIC -> stocks.stream().map(StockBasicDTO::new).toList();
            case DETAILED -> stocks.stream().map(StockDetailedDTO::new).toList();
            case FULL -> stocks.stream().map(StockFullDTO::new).toList();
        };
    }
}
