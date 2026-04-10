package io.github.stockmarket.market.indexfunds.exception;

import io.github.stockmarket.core.exception.BadRequestException;

public class IndexFundException extends BadRequestException {

    private IndexFundException(String message) {
        super(message, "IndexFundController");
    }

    public static IndexFundException invalidMarketCap(String cap){
        return new IndexFundException(
                String.format("{%s} not a market cap. Must be one of: Small, Mid, Large", cap)
        );
    }

    public static IndexFundException invalidSector(String sector){
        return new IndexFundException(
                String.format("No stocks have sector {%s}", sector)
        );
    }

    public static IndexFundException invalidVolatility(String volatility){
        return new IndexFundException(
                String.format("{%s} not a volatility type", volatility)
        );
    }
}
