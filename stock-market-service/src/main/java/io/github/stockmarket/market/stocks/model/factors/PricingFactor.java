package io.github.stockmarket.market.stocks.model.factors;

import lombok.Getter;
import lombok.Setter;

import static io.github.stockmarket.market.stocks.utils.PricingModelUtils.randomPriceDelta;

@Getter
@Setter
public abstract class PricingFactor {
    protected int value;
    protected double weight;
    protected double baseNoise;

    public PricingFactor(int value, double weight, double noise) {
        this.value = clamp(value);
        this.weight = weight;
        this.baseNoise = noise;
    }

    public double computeDelta(double currentPrice) {
        return randomPriceDelta(currentPrice, getWeightedValue(), baseNoise);
    }

    protected double getWeightedValue() {
        return value * weight;
    }

    protected int clamp(int rawValue) {
        //return Math.max(-ABSOLUTE_VALUE_FACTOR_RANGE, Math.min(rawValue, ABSOLUTE_VALUE_FACTOR_RANGE));
        return 0;
    }
}
