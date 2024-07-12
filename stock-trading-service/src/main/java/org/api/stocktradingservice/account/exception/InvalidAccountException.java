package org.api.stocktradingservice.account.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidAccountException extends RuntimeException {

    private final HttpStatus status = HttpStatus.BAD_REQUEST;

    public InvalidAccountException(String message) {
        super(message);
    }
}
