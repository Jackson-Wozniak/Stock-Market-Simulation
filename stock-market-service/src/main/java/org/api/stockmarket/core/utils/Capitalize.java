package org.api.stockmarket.core.utils;

public class Capitalize {

    public static String capitalize(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
    }
}
