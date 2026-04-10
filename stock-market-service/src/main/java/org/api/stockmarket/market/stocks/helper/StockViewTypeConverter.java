package org.api.stockmarket.market.stocks.helper;

import org.api.stockmarket.market.stocks.enums.StockViewType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StockViewTypeConverter implements Converter<String, StockViewType> {
    @Override
    public StockViewType convert(String source) {
        return StockViewType.fromString(source);
    }
}
