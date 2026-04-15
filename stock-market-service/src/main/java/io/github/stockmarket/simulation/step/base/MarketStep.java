package io.github.stockmarket.simulation.step.base;

import io.github.stockmarket.market.core.model.MarketContext;
import io.github.stockmarket.simulation.rules.MarketRules;

/*
MarketSteps represent a single, discrete market evolution event. For example a PriceMovementStep is
time-independent, and it is intended that the call of PriceMovementStep.apply(context) represents the singular
movement of the markets prices. The orchestration that logic that decides the number of times this step is called
during the day is the deciding factor in how much activity the market experiences in a day
 */
public abstract class MarketStep {
    protected final MarketRules rules;

    protected MarketStep(MarketRules rules){
        this.rules = rules;
    }

    public abstract void apply(MarketContext context);
}
