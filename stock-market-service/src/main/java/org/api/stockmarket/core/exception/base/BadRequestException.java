package org.api.stockmarket.core.exception.base;

import org.springframework.http.HttpStatus;

public abstract class BadRequestException extends BaseException{

    public BadRequestException(String message, String controllerName){
        super(HttpStatus.BAD_REQUEST, message, controllerName);
    }
}
