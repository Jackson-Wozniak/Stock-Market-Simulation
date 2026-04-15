package io.github.stockmarket.market.stocks.utils;

import java.util.Random;

public class PricingModelUtils {
    private static final Random random = new Random();

    public static double randomPriceDelta(double currentPrice, double weightedFactor, double noise){
//        double signal = Math.tanh(weightedFactor / PRICE_SIGNAL_DIMINISHER);
//
//        //standard deviation comes from sigma, where volatility dictates it
//        double sigma = noise * (1 - (.7 * Math.abs(signal)));
//        double minSigma = 0.05;
//        sigma = Math.max(minSigma, sigma);
//
//        double randomizedNoise = (random.nextDouble() * 2 - 1) * sigma;
//
//        double dampener = 1.0 / (1.0 + Math.pow(currentPrice / PRICE_CEILING_DAMPENER, 1.5));
//
//        double priceDelta = ((signal * .06) + randomizedNoise) * currentPrice;
//
//        return (priceDelta * dampener) / PRICE_SCALE_DIMINISHER;
        return 0;
    }
}
