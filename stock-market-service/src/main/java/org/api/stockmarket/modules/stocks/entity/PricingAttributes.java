package org.api.stockmarket.modules.stocks.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.stockmarket.modules.stocks.enums.PriceVolatility;

import static org.api.stockmarket.modules.stocks.utils.PricingModelUtils.*;
import static org.api.stockmarket.engine.properties.MarketEnvironmentProperties.ABSOLUTE_VALUE_FACTOR_RANGE;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class PricingAttributes {
    @Column(name = "pricing_volatility")
    @Enumerated(EnumType.STRING)
    private PriceVolatility volatility;

    @Column(name = "investor_confidence_factor")
    private int investorConfidenceFactor;

    @Column(name = "investor_confidence_weight")
    private double investorConfidenceWeight;

    @Column(name = "base_investor_confidence_noise")
    private double baseInvestorConfidenceNoise;

    @Column(name = "news_sentiment_factor")
    private int newsSentimentFactor;

    @Column(name = "news_sentiment_weight")
    private double newsSentimentWeight;

    @Column(name = "base_news_sentiment_noise")
    private double baseNewsSentimentNoise;

    @Column(name = "innovation_factor")
    private int innovationFactor;

    @Column(name = "innovation_weight")
    private double innovationWeight;

    @Column(name = "base_innovation_noise")
    private double baseInnovationNoise;

    @Column(name = "trading_demand_factor")
    private int tradingDemandFactor;

    @Column(name = "trading_demand_weight")
    private double tradingDemandWeight;

    @Column(name = "base_trading_demand_noise")
    private double baseTradingDemandNoise;

    @Column(name = "liquidity_factor")
    private int liquidityFactor;

    @Column(name = "liquidity_weight")
    private double liquidityWeight;

    @Column(name = "base_liquidity_noise")
    private double baseLiquidityNoise;

    public PricingAttributes(PriceVolatility volatility, int investorConfidenceFactor,
                             double investorConfidenceWeight, double baseInvestorConfidenceNoise,
                             int newsSentimentFactor, double newsSentimentWeight, double baseNewsSentimentNoise,
                             int innovationFactor, double innovationWeight, double baseInnovationNoise,
                             int tradingDemandFactor, double tradingDemandWeight, double baseTradingDemandNoise,
                             int liquidityFactor, double liquidityWeight, double baseLiquidityNoise) {
        this.volatility = volatility;
        this.investorConfidenceFactor = clampFactor(investorConfidenceFactor);
        this.investorConfidenceWeight = investorConfidenceWeight;
        this.baseInvestorConfidenceNoise = baseInvestorConfidenceNoise;
        this.newsSentimentFactor = clampFactor(newsSentimentFactor);
        this.newsSentimentWeight = newsSentimentWeight;
        this.baseNewsSentimentNoise = baseNewsSentimentNoise;
        this.innovationFactor = clampFactor(innovationFactor);
        this.innovationWeight = innovationWeight;
        this.baseInnovationNoise = baseInnovationNoise;
        this.tradingDemandFactor = clampFactor(tradingDemandFactor);
        this.tradingDemandWeight = tradingDemandWeight;
        this.baseTradingDemandNoise = baseTradingDemandNoise;
        this.liquidityFactor = clampFactor(liquidityFactor);
        this.liquidityWeight = liquidityWeight;
        this.baseLiquidityNoise = baseLiquidityNoise;
    }

    private static int clampFactor(int rawValue){
        return Math.max(-ABSOLUTE_VALUE_FACTOR_RANGE,
                Math.min(rawValue, ABSOLUTE_VALUE_FACTOR_RANGE));
    }

    public double calculateNewsDelta(double currentPrice){
        return randomPriceDelta(currentPrice, weightedNewsSentimentFactor(),
                volatility.applyMagnitude(baseNewsSentimentNoise));
    }

    public double calculateInvestorConfidenceDelta(double currentPrice){
        return randomPriceDelta(currentPrice, weightedInvestorConfidenceFactor(),
                volatility.applyMagnitude(baseInvestorConfidenceNoise));
    }

    public double calculateInnovationDelta(double currentPrice){
        return randomPriceDelta(currentPrice, weightedInnovationFactor(),
                volatility.applyMagnitude(baseInnovationNoise));
    }

    public double calculateTradingDemandDelta(double currentPrice){
        return randomPriceDelta(currentPrice, weightedTradingDemandFactor(),
                volatility.applyMagnitude(baseTradingDemandNoise));
    }

    public double calculateLiquidityDelta(double currentPrice){
        return randomPriceDelta(currentPrice, weightedLiquidityFactor(),
                volatility.applyMagnitude(baseLiquidityNoise));
    }

    private double weightedInvestorConfidenceFactor(){
        return investorConfidenceFactor * investorConfidenceWeight;
    }

    private double weightedNewsSentimentFactor(){
        return newsSentimentFactor * newsSentimentWeight;
    }

    private double weightedInnovationFactor(){
        return innovationFactor * innovationWeight;
    }

    private double weightedTradingDemandFactor(){
        return tradingDemandFactor * tradingDemandWeight;
    }

    private double weightedLiquidityFactor(){
        return liquidityFactor * liquidityWeight;
    }
}
