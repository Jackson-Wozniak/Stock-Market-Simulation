package io.github.stockmarket.market.stocks.model.factors;

public class NewsSentimentFactor extends PricingFactor{
    public NewsSentimentFactor(int value, double weight, double noise) {
        super(value, weight, noise);
    }
}
