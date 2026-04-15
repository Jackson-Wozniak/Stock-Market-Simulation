package io.github.stockmarket.simulation.step;

import io.github.stockmarket.market.core.enums.MarketPhase;
import io.github.stockmarket.market.core.model.MarketContext;
import io.github.stockmarket.simulation.rules.MarketRules;
import lombok.Getter;

/*
EndOfDaySteps represent the same functionality as MarketStep, however they are intended to be run at the EndOfDay
time. If we are using a procedural approach to simulation, it would essentially be like:
for(day in daysSimulated){
    for(tick in ticksPerDay){
        foreach(m in MarketStep) m.apply(context)
    }
    foreach(e in EndOfDayStep) e.apply(context)
}
This would be intended for logic such as price archiving, large sentiment shifts, etc.
 */
@Getter
public abstract class DailyStep extends MarketStep{
    private final MarketPhase marketPhase;

    protected DailyStep(MarketRules rules, MarketPhase marketPhase) {
        super(rules);
        this.marketPhase = marketPhase;
    }

    public abstract void apply(MarketContext context);
}
