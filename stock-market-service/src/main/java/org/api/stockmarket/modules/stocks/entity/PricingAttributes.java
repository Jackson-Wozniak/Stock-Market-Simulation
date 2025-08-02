package org.api.stockmarket.modules.stocks.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.stockmarket.modules.stocks.enums.PriceVolatility;

import static org.api.stockmarket.modules.stocks.utils.PricingModelUtils.*;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
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

    /*
    TODO:
        This class will store the price info for a stock. This allows for pulling only this
            entity when we want to change prices to avoid constant DB JOINs being made for a stock.
            This also highlights the fact that company doesn't matter, only price factors do.
            Also encapsulates the change price implementation.
        this class should provide the factors involved with the price change formula.
        Can utilize fundamentals/finances if they are modeled in the future, but provides
        the important factors involving the direction, scale, and randomness of price changes.

        Fundamental Fair Value (? may be too complex)
            the fair value given to a stock, based on all fundamentals

        Price Gravity (not needed if fundamental fair value isn't implemented
            the likelihood a stock will trend toward its fair value, regardless of other factors

        Trading Confidence
            Primarily determines the short term attractiveness of a stock,

        Investor/Financial Sentiment
            A more long term view of the companies health

        Volatility
            The scale at which price changes, regardless of direction

        Demand Factor
            The short-term demand for a stock, can be coupled closely with liquidity to model
            an in-demand stock running out of buying liquidity

        Liquidity
            How easy it is to trade the stock, how many shares are available

        News/Trading Sentiment
            The short-term after-effect of news events

        Speculative Pressure
            Growth vs. Value vs. Stable vs. Meme etc. Can be coupled with volatility to
                alter how much speculation is involved with the stock

        Innovation Factor
            The scale at which a company is expected to innovate. Higher innovation companies
                can be viewed as more 'growth' stocks and therefore may see more positive
                price movements regardless of other factors

        Rebound Tendency
            Likelihood a stock will rebound after taking consecutive losses.

        COULD ALSO BE INCLUDED?
            Macro influences
                determines whether macro changes (sector related factors, total market factors
                etc. have major changes on the direction of the company)

        IMPORTANT TODO:
            To increase realism, short term factors, especially news sentiment
            should regress toward the mean over time, to simulate the fact
            that news from a company will eventually become irrelevant. This ensures
            that long term factors are the focus
     */

    /*
    Right now it seems like this formula is really able to highlight the
        distinct changes from the factor, where values closer to
        -50 brought it down to 46 (from $100) after 10000 simultions, whereas
        -1 as the factor kept it at almost exactly $100.

        Going further with this may be the best way to model the prices
     */
//    public void changePrice(){
////        double newsFactorChange = ((newsSentimentFactor / 50.0) +
////                volatility.applyNoise(baseNewsSentimentNoise));
//
//        //altering the value in tanh affects range (/ 25.0 would mean less numbers
//        //are closer to -1 or 1 than if you did /10.0)
//        //narrowing the range of factors (instead of -50 to 50) may work better
//        double normalizedInput = newsSentimentFactor / 25.0; // now roughly in [-2, 2]
//
//        double signal = Math.tanh(normalizedInput);
//
//        double noise = volatility.applyMagnitude(baseNewsSentimentNoise);
//
//        //standard deviation comes from sigma, where volatility dictates it
//        double sigma = noise * (1 - (.75 * Math.abs(signal)));
//
//        double totalNoise = (random.nextDouble() * 2 - 1) * sigma;
//
//        double change = ((signal * .07) + totalNoise) * currentPrice;
//
//        //System.out.println(change);
//        double tickScale = 1000.0; //as a percent
//        setCurrentPrice(currentPrice + (change / tickScale));
//    }
}
