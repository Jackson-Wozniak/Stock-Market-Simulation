package org.api.stockmarket.market.utils;

public class DateConversion {

    public static String incrementMarketDay(String date){
        String[] dateAsArray = date.split("/");

        int day = Integer.parseInt(dateAsArray[1]);
        int month = Integer.parseInt(dateAsArray[0]);
        int year = Integer.parseInt(dateAsArray[2]);

        if(day >= 30){
            day = 1;
            if(month >= 12){
                month = 1;
                year++;
            }else{
                month++;
            }
        }else{
            day++;
        }
        return formatDateIntegersAsString(month, day, year);
    }

    public static String formatDateIntegersAsString(int month, int day, int year){
        return month + "/" + day + "/" + year;
    }

    public static boolean dateIsFormattedCorrectly(String date){
        String[] dateArray = date.split("/");
        int month, day;
        try{
            month = Integer.parseInt(dateArray[0]);
            day = Integer.parseInt(dateArray[1]);
            Integer.parseInt(dateArray[2]);
        }catch(Exception ex){
            return false;
        }

        return day <= 30 && month <= 12;
    }
}