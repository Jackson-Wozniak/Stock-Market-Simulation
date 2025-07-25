package org.api.stockmarket.indexfund.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class IndexFundException extends RuntimeException {

    private final HttpStatus status;

    private IndexFundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public static IndexFundException invalidMarketCap(String cap){
        return new IndexFundException(
                String.format("{%s} not a market cap. Must be one of: Small, Mid, Large", cap),
                HttpStatus.BAD_REQUEST
        );
    }

    public static IndexFundException invalidSector(String sector){
        return new IndexFundException(
                String.format("No stocks have sector {%s}", sector),
                HttpStatus.BAD_REQUEST
        );
    }

    public static IndexFundException invalidVolatility(String volatility){
        return new IndexFundException(
                String.format("{%s} not a volatility type", volatility),
                HttpStatus.BAD_REQUEST
        );
    }
}
