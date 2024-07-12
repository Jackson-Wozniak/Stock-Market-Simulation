package org.api.stocktradingservice.account.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
public class AccountBalanceException extends RuntimeException {

    private final HttpStatus status = HttpStatus.BAD_REQUEST;

    public AccountBalanceException(String message) {
        super(message);
    }
}
