package org.api.tradinggame.account.utils;

import java.text.DecimalFormat;

public class CalculateCostBasisAndProfits {

    private static final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public static double newCostBasis(
            int stocksOwned, int amountToBuy, double currentCostBasis, double stockPrice) {
        double current = stocksOwned * currentCostBasis;
        double buyBasis = amountToBuy * stockPrice;
        double total = current + buyBasis;

        return roundToTwoDecimalPlaces(total / (stocksOwned + amountToBuy));
    }

    public static double findProfitsAfterSelling(
            double accountProfits, double costBasis, int stocksToSell, double currentPrice) {
        double transactionProfits = (currentPrice - costBasis) * stocksToSell;
        return roundToTwoDecimalPlaces(accountProfits + transactionProfits);
    }

    public static double roundToTwoDecimalPlaces(double notRounded) {
        return Double.parseDouble(decimalFormat.format(notRounded));
    }
}
