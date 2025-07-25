package org.api.stockmarket.engine.exception.base;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class BaseException extends RuntimeException{
    private final HttpStatus status;
    private final String controllerName;

    protected BaseException(HttpStatus status, String message, String controllerName){
        super("From " + controllerName + ": " + message);
        this.status = status;
        this.controllerName = controllerName;
    }
}
