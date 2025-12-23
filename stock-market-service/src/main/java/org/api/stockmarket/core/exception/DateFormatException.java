package org.api.stockmarket.core.exception;

public class DateFormatException extends BadRequestException {
    private static final String MESSAGE = "Date in request query is formatted incorrectly. Ensure format of MM/dd/yyyy";

    public DateFormatException() {
        super(MESSAGE, "MarketController");
    }
}
