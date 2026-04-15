package io.github.stockmarket.market.stocks.model;

import io.github.stockmarket.market.stocks.model.factors.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import io.github.stockmarket.market.stocks.enums.PriceVolatility;

import static io.github.stockmarket.market.stocks.utils.PricingModelUtils.*;

@Getter
@Setter
@NoArgsConstructor
public class PricingAttributes {
    private PriceVolatility volatility;
    private InvestorConfidenceFactor investorConfidenceFactor;
    private NewsSentimentFactor newsSentimentFactor;
    private InnovationFactor innovationFactor;
    private TradingDemandFactor tradingDemandFactor;
    private LiquidityFactor liquidityFactor;

    public PricingAttributes(PriceVolatility volatility,
                             InvestorConfidenceFactor investorConfidenceFactor,
                             NewsSentimentFactor newsSentimentFactor,
                             InnovationFactor innovationFactor,
                             TradingDemandFactor tradingDemandFactor,
                             LiquidityFactor liquidityFactor) {
        this.volatility = volatility;
        this.investorConfidenceFactor = investorConfidenceFactor;
        this.newsSentimentFactor = newsSentimentFactor;
        this.innovationFactor = innovationFactor;
        this.tradingDemandFactor = tradingDemandFactor;
        this.liquidityFactor = liquidityFactor;
    }
}
