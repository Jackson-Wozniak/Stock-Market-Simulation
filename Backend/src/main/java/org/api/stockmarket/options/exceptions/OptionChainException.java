package org.api.stockmarket.options.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class OptionChainException extends RuntimeException{
    public OptionChainException(String message){
        super(message);
    }
}
