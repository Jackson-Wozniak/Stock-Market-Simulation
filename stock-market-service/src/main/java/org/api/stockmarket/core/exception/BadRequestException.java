package org.api.stockmarket.core.exception;

import org.api.stockmarket.core.exception.base.BaseException;
import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseException {

    public BadRequestException(String message, String controllerName){
        super(HttpStatus.BAD_REQUEST, message, controllerName);
    }

    public BadRequestException(String message){
        super(HttpStatus.BAD_REQUEST, message, "Unknown");
    }
}
