package org.api.stockmarket.stocks.earnings.utils;

import org.api.stockmarket.stocks.stock.entity.Stock;

import java.text.DecimalFormat;
import java.util.Random;

public class FindEarningsPerShare {

    private static final Random random = new Random();
    private static final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public static double getEstimatedEarningsPerShare() {
        return Double.parseDouble(decimalFormat.format(random.nextDouble(1, 3)));
    }

    public static double getActualEarningsPerShare(Stock stock) {
        return Double.parseDouble(decimalFormat.format(random.nextDouble(2, 5)));
    }
}
