package org.api.stockmarket.market.utils;

import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

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

    public static String toFormattedString(ZonedDateTime date){
        String month = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        int day = date.getDayOfMonth();
        int year = date.getYear();

        String ordinalIndicator = switch (day % 10) {
            case 1 -> "st";
            case 2 -> "nd";
            case 3 -> "rd";
            default -> "th";
        };

        return month + " " + day + ordinalIndicator + ", " + year;
    }
}