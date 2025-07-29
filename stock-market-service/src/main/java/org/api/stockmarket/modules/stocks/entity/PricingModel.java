package org.api.stockmarket.modules.stocks.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.stockmarket.modules.stocks.enums.PriceVolatility;

import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
public class PricingModel {
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
     */
    private double currentPrice;

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

    public PricingModel(double currentPrice, PriceVolatility volatility,
                        int investorConfidenceFactor, double investorConfidenceWeight,
                        double baseInvestorConfidenceNoise, int newsSentimentFactor,
                        double newsSentimentWeight, double baseNewsSentimentNoise,
                        int innovationFactor, double innovationWeight,
                        double baseInnovationNoise, int tradingDemandFactor,
                        double tradingDemandWeight, double baseTradingDemandNoise,
                        int liquidityFactor, double liquidityWeight,
                        double baseLiquidityNoise) {
        this.currentPrice = currentPrice;
        this.volatility = volatility;
        this.investorConfidenceFactor = investorConfidenceFactor;
        this.investorConfidenceWeight = investorConfidenceWeight;
        this.baseInvestorConfidenceNoise = baseInvestorConfidenceNoise;
        this.newsSentimentFactor = newsSentimentFactor;
        this.newsSentimentWeight = newsSentimentWeight;
        this.baseNewsSentimentNoise = baseNewsSentimentNoise;
        this.innovationFactor = innovationFactor;
        this.innovationWeight = innovationWeight;
        this.baseInnovationNoise = baseInnovationNoise;
        this.tradingDemandFactor = tradingDemandFactor;
        this.tradingDemandWeight = tradingDemandWeight;
        this.baseTradingDemandNoise = baseTradingDemandNoise;
        this.liquidityFactor = liquidityFactor;
        this.liquidityWeight = liquidityWeight;
        this.baseLiquidityNoise = baseLiquidityNoise;
    }

    private static final Random random = new Random();

    /*
    Right now it seems like this formula is really able to highlight the
        distinct changes from the factor, where values closer to
        -50 brought it down to 46 (from $100) after 10000 simultions, whereas
        -1 as the factor kept it at almost exactly $100.

        Going further with this may be the best way to model the prices
     */
    public void changePrice(){
//        double newsFactorChange = ((newsSentimentFactor / 50.0) +
//                volatility.applyNoise(baseNewsSentimentNoise));

        //altering the value in tanh affects range (/ 25.0 would mean less numbers
        //are closer to -1 or 1 than if you did /10.0)
        //narrowing the range of factors (instead of -50 to 50) may work better
        double newsFactorScale = Math.tanh(newsSentimentFactor / 50.0) * .1;
        double noise = volatility.applyMagnitude(baseNewsSentimentNoise);

        //standard deviation comes from sigma, where volatility dictates it
        double sigma = noise * (1 - Math.abs(newsFactorScale / .1));

        double totalNoise = random.nextGaussian() * sigma;

        double change = (newsFactorScale + totalNoise) * currentPrice;

        //System.out.println(change);
        double tickScale = 1000.0; //as a percent
        setCurrentPrice(currentPrice + (change / tickScale));
    }
}
