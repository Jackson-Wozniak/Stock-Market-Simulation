package org.api.stockmarket.modules.stocks.helper;

import org.api.stockmarket.modules.stocks.enums.StockViewType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class StockViewTypeConverter implements Converter<String, StockViewType> {
    @Override
    public StockViewType convert(String source) {
        return StockViewType.fromString(source);
    }
}
