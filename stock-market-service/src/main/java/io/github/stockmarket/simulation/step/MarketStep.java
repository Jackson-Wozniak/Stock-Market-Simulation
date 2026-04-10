package io.github.stockmarket.simulation.step;

import io.github.stockmarket.simulation.model.MarketContext;

/*
MarketSteps represent a single, discrete market evolution event. For example a PriceMovementStep is
time-independent, and it is intended that the call of PriceMovementStep.apply(context) represents the singular
movement of the markets prices. The orchestration that logic that decides the number of times this step is called
during the day is the deciding factor in how much activity the market experiences in a day
 */
public interface MarketStep {
    void apply(MarketContext context);
}
