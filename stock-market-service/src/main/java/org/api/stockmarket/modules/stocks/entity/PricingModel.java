package org.api.stockmarket.modules.stocks.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.stockmarket.modules.stocks.enums.PriceVolatility;

import java.math.BigDecimal;
import java.util.Random;

@Entity(name = "pricingModel")
@Table(name = "pricing_models")
@Getter
@Setter
@NoArgsConstructor
public class PricingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Column(name = "price")
    private BigDecimal price;

    @Embedded
    private PricingAttributes attributes;

    private static final Random random = new Random();

    public PricingModel(Builder builder) {
        this.stock = builder.stock;
        this.price = new BigDecimal(builder.price);
        this.attributes = new PricingAttributes(
            builder.volatility,
            builder.investorConfidenceFactor,
            builder.investorConfidenceWeight,
            builder.baseInvestorConfidenceNoise,
            builder.newsSentimentFactor,
            builder.newsSentimentWeight,
            builder.baseNewsSentimentNoise,
            builder.innovationFactor,
            builder.innovationWeight,
            builder.baseInnovationNoise,
            builder.tradingDemandFactor,
            builder.tradingDemandWeight,
            builder.baseTradingDemandNoise,
            builder.liquidityFactor,
            builder.liquidityWeight,
            builder.baseLiquidityNoise
        );
    }

    public void runPriceChange(){
        double currentPrice = price.doubleValue();
        double newsFactorDelta = attributes.calculateNewsDelta(currentPrice);
        double investorConfidenceDelta = attributes.calculateInvestorConfidenceDelta(currentPrice);
        double innovationDelta = attributes.calculateInnovationDelta(currentPrice);
        double tradingDemandDelta = attributes.calculateTradingDemandDelta(currentPrice);
        double liquidityDelta = attributes.calculateLiquidityDelta(currentPrice);

        double totalDelta = newsFactorDelta + investorConfidenceDelta + innovationDelta
                + tradingDemandDelta + liquidityDelta;
        setPrice(BigDecimal.valueOf(currentPrice + totalDelta));
    }

    public double getPriceValue(){
        return this.price.doubleValue();
    }

    public static class Builder{
        private final Stock stock;
        private double price;
        private PriceVolatility volatility;

        private int investorConfidenceFactor;
        private double investorConfidenceWeight;
        private double baseInvestorConfidenceNoise;

        private int newsSentimentFactor;
        private double newsSentimentWeight;
        private double baseNewsSentimentNoise;

        private int innovationFactor;
        private double innovationWeight;
        private double baseInnovationNoise;

        private int tradingDemandFactor;
        private double tradingDemandWeight;
        private double baseTradingDemandNoise;

        private int liquidityFactor;
        private double liquidityWeight;
        private double baseLiquidityNoise;

        public Builder(Stock stock){
            this.stock = stock;
        }

        public Builder details(double startingPrice, PriceVolatility v){
            this.price = startingPrice;
            this.volatility = v;
            return this;
        }

        public Builder investorConfidence(int factor, double weight, double baseNoise){
            this.investorConfidenceFactor = factor;
            this.investorConfidenceWeight = weight;
            this.baseInvestorConfidenceNoise = baseNoise;
            return this;
        }

        public Builder newsSentiment(int factor, double weight, double baseNoise){
            this.newsSentimentFactor = factor;
            this.newsSentimentWeight = weight;
            this.baseNewsSentimentNoise = baseNoise;
            return this;
        }

        public Builder innovation(int factor, double weight, double baseNoise){
            this.innovationFactor = factor;
            this.innovationWeight = weight;
            this.baseInnovationNoise = baseNoise;
            return this;
        }

        public Builder tradingDemand(int factor, double weight, double baseNoise){
            this.tradingDemandFactor = factor;
            this.tradingDemandWeight = weight;
            this.baseTradingDemandNoise = baseNoise;
            return this;
        }

        public Builder liquidity(int factor, double weight, double baseNoise){
            this.liquidityFactor = factor;
            this.liquidityWeight = weight;
            this.baseLiquidityNoise = baseNoise;
            return this;
        }

        public PricingModel build(){
            return new PricingModel(this);
        }
    }
}
