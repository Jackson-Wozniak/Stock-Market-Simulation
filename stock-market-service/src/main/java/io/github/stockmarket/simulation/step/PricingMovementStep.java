package io.github.stockmarket.simulation.step;

import io.github.stockmarket.market.stocks.model.Stock;
import io.github.stockmarket.market.core.model.MarketContext;
import io.github.stockmarket.simulation.helper.PricingMovementHelper;
import io.github.stockmarket.simulation.rules.MarketRules;
import io.github.stockmarket.simulation.step.base.MarketStep;

public class PricingMovementStep extends MarketStep {
    private final PricingMovementHelper pricingMovementHelper;

    public PricingMovementStep(MarketRules rules){
        super(rules);
        this.pricingMovementHelper = new PricingMovementHelper(rules.getPricingMovementRules());
    }

    @Override
    public void apply(MarketContext context){
        context.getStocks().forEach(stock -> {
            double delta = calculatePriceDelta(stock);
            stock.applyPriceDelta(delta);
        });
    }

    private double calculatePriceDelta(Stock stock){
        double currentPrice = stock.getPrice();
        double newsFactorDelta = pricingMovementHelper.computeFactorDelta(
                currentPrice, stock.getPricingModel().getAttributes().getNewsSentimentFactor());
        double investorConfidenceDelta = pricingMovementHelper.computeFactorDelta(
                currentPrice, stock.getPricingModel().getAttributes().getInvestorConfidenceFactor());
        double innovationDelta = pricingMovementHelper.computeFactorDelta(
                currentPrice, stock.getPricingModel().getAttributes().getInnovationFactor());
        double tradingDemandDelta = pricingMovementHelper.computeFactorDelta(
                currentPrice, stock.getPricingModel().getAttributes().getTradingDemandFactor());
        double liquidityDelta = pricingMovementHelper.computeFactorDelta(
                currentPrice, stock.getPricingModel().getAttributes().getLiquidityFactor());

        return newsFactorDelta + investorConfidenceDelta + innovationDelta
                + tradingDemandDelta + liquidityDelta;
    }
}
