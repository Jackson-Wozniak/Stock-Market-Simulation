package org.api.stockmarket.market.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DateFormatException extends RuntimeException {

    private final HttpStatus status = HttpStatus.BAD_REQUEST;

    public DateFormatException() {
        super("Date Is Not Formatted Correctly");
    }
}
