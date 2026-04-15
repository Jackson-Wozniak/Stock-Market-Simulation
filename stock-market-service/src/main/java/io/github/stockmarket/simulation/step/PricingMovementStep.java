package io.github.stockmarket.simulation.step;

import io.github.stockmarket.market.stocks.model.Stock;
import io.github.stockmarket.market.core.model.MarketContext;
import io.github.stockmarket.simulation.rules.MarketRules;

public class PricingMovementStep extends MarketStep {

    public PricingMovementStep(MarketRules rules){
        super(rules);
    }

    @Override
    public void apply(MarketContext context){
        context.getStocks().forEach(Stock::runPriceChange);
    }
}
