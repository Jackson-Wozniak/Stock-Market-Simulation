package io.github.stockmarket.market.stocks.model.factors;

public class InvestorConfidenceFactor extends PricingFactor{
    public InvestorConfidenceFactor(int value, double weight, double noise) {
        super(value, weight, noise);
    }
}
