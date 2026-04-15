package io.github.stockmarket.market.stocks.model.factors;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class PricingFactor {
    protected int value;
    protected double weight;
    protected double baseNoise;

    public PricingFactor(int value, double weight, double noise) {
        this.value = value;
        this.weight = weight;
        this.baseNoise = noise;
    }

    public double getWeightedValue() {
        return value * weight;
    }

    /*
    TODO: not currently in use, however once factor value updates are done this should be added to a pricing helper
        to ensure that the market rules dictate max/min factor value
    */
//    protected int clamp(int rawValue) {
//        //return Math.max(-ABSOLUTE_VALUE_FACTOR_RANGE, Math.min(rawValue, ABSOLUTE_VALUE_FACTOR_RANGE));
//        return 0;
//    }
}
