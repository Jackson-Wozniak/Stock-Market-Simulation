package io.github.stockmarket.market.stocks.model;

import io.github.stockmarket.market.stocks.model.factors.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import io.github.stockmarket.market.stocks.enums.PriceVolatility;

import java.math.BigDecimal;
import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
public class PricingModel {
    private Stock stock;
    private BigDecimal price;
    private PricingAttributes attributes;

    public PricingModel(Builder builder) {
        this.stock = builder.stock;
        this.price = new BigDecimal(builder.price);
        this.attributes = new PricingAttributes(
                builder.volatility,
                builder.investorConfidenceFactor,
                builder.newsSentimentFactor,
                builder.innovationFactor,
                builder.tradingDemandFactor,
                builder.liquidityFactor
        );
    }

    public double getPriceValue(){
        return this.price.doubleValue();
    }

    public static class Builder{
        private final Stock stock;
        private double price;
        private PriceVolatility volatility;
        private InvestorConfidenceFactor investorConfidenceFactor;
        private InnovationFactor innovationFactor;
        private LiquidityFactor liquidityFactor;
        private NewsSentimentFactor newsSentimentFactor;
        private TradingDemandFactor tradingDemandFactor;

        public Builder(Stock stock){
            this.stock = stock;
        }

        public Builder details(double startingPrice, PriceVolatility v){
            this.price = startingPrice;
            this.volatility = v;
            return this;
        }

        public Builder investorConfidence(int factor, double weight, double baseNoise){
            this.investorConfidenceFactor = new InvestorConfidenceFactor(factor, weight, baseNoise);
            return this;
        }

        public Builder newsSentiment(int factor, double weight, double baseNoise){
            this.newsSentimentFactor = new NewsSentimentFactor(factor, weight, baseNoise);
            return this;
        }

        public Builder innovation(int factor, double weight, double baseNoise){
            this.innovationFactor = new InnovationFactor(factor, weight, baseNoise);
            return this;
        }

        public Builder tradingDemand(int factor, double weight, double baseNoise){
            this.tradingDemandFactor = new TradingDemandFactor(factor, weight, baseNoise);
            return this;
        }

        public Builder liquidity(int factor, double weight, double baseNoise){
            this.liquidityFactor = new LiquidityFactor(factor, weight, baseNoise);
            return this;
        }

        public PricingModel build(){
            return new PricingModel(this);
        }
    }
}
