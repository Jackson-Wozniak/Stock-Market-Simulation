package org.api.stockmarket.modules.stocks.csv;

import lombok.NoArgsConstructor;
import org.api.stockmarket.common.csv.*;
import org.api.stockmarket.modules.stocks.entity.Stock;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class StockCSVReader implements CSVReader<StockCSVObject, Stock> {
    private static final String FILE_PATH = "text/stocks.csv";

    @Override
    public List<StockCSVObject> map() {
        List<String[]> lines = CSVUtils.fileContents(FILE_PATH, getClass().getName());
        return lines.stream().map(StockCSVObject::new).toList();
    }

    @Override
    public List<Stock> toEntity(List<StockCSVObject> data) {
        return data.stream().map(StockCSVObject::mapToEntity).toList();
    }

    @Override
    public List<Stock> toEntity() {
        return map().stream().map(StockCSVObject::mapToEntity).toList();
    }

    @Override
    public long lineCount(){
        return CSVUtils.lineCount(FILE_PATH, getClass().getName());
    }
}
