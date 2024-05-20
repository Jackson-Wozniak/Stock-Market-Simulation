package org.api.stockmarket.indexfund.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class IndexFundException extends RuntimeException {

    public IndexFundException() {
        super("No Index Fund Found");
    }
}
