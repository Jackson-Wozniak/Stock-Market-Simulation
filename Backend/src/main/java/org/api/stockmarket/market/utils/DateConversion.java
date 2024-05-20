package org.api.stockmarket.market.utils;

import java.time.ZonedDateTime;

public class DateConversion {

    /**
     * Is the date the last day of the month?
     * Looks only 
     * @param date  ZonedDateTime
     * @return True if the last day of the month
     */
    public static boolean isLastDayMonth(ZonedDateTime date) {
        var checkDate = date.toLocalDate();
        var lastDayOfMonthDate = checkDate.withDayOfMonth(checkDate.getMonth().length(checkDate.isLeapYear()));
        return checkDate.isEqual(lastDayOfMonthDate);
    }

}