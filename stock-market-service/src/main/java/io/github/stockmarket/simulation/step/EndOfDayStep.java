package io.github.stockmarket.simulation.step;

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
public interface EndOfDayStep extends MarketStep{
}
