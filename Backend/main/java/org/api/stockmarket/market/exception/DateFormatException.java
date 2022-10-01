package org.api.stockmarket.market.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DateFormatException extends RuntimeException {

    public DateFormatException() {
        super("Date Is Not Formatted Correctly");
    }
}
