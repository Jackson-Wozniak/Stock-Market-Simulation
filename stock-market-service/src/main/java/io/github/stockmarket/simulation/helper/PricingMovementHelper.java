package io.github.stockmarket.simulation.helper;

import io.github.stockmarket.market.stocks.model.factors.PricingFactor;
import io.github.stockmarket.simulation.rules.MarketRules;
import io.github.stockmarket.simulation.rules.PricingMovementRules;

import java.util.Random;

public class PricingMovementHelper {
    private final PricingMovementRules rules;
    private static final Random random = new Random();

    public PricingMovementHelper(PricingMovementRules rules){
        this.rules = rules;
    }

    public double computeFactorDelta(double currentPrice, PricingFactor factor){
        double signal = Math.tanh(factor.getWeightedValue() / rules.getPriceSignalDiminisher());

        //standard deviation comes from sigma, where volatility dictates it
        double sigma = factor.getBaseNoise() * (1 - (.7 * Math.abs(signal)));
        double minSigma = 0.05;
        sigma = Math.max(minSigma, sigma);

        double randomizedNoise = (random.nextDouble() * 2 - 1) * sigma;

        double dampener = 1.0 / (1.0 + Math.pow(currentPrice / rules.getPriceCeilingDampener(), 1.5));

        double priceDelta = ((signal * .06) + randomizedNoise) * currentPrice;

        return (priceDelta * dampener) / rules.getPriceScaleDiminisher();
    }
}
