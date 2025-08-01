package org.api.stockmarket.modules.stocks.factory;

import lombok.AllArgsConstructor;
import org.api.stockmarket.modules.stocks.csv.StockCSVObject;
import org.api.stockmarket.modules.stocks.entity.Stock;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StockFactory {

    public Stock create(StockCSVObject csvData){
        return null;
    }
}
