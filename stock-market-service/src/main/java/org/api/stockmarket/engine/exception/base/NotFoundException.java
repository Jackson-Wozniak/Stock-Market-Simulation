package org.api.stockmarket.engine.exception.base;

import org.springframework.http.HttpStatus;

public abstract class NotFoundException extends BaseException{

    protected NotFoundException(String message, String controllerName){
        super(HttpStatus.NOT_FOUND, message, controllerName);
    }
}
