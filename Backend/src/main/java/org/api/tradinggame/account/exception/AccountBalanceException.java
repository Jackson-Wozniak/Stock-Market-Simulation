package org.api.tradinggame.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid account balance for transaction")
public class AccountBalanceException extends RuntimeException {

    public AccountBalanceException(String message) {
        super(message);
    }
}
