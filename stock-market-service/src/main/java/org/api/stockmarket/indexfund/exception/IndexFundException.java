package org.api.stockmarket.indexfund.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class IndexFundException extends RuntimeException {

    private final HttpStatus status = HttpStatus.NOT_FOUND;

    public IndexFundException() {
        super("No Index Fund Found");
    }
}
