package stocktradingsimulator.indexfund.utils;

import java.util.Locale;

public class Capitalize {

    public static String capitalize(String string){
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }
}
