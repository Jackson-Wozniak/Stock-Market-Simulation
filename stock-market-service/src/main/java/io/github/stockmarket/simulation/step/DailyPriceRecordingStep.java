package io.github.stockmarket.simulation.step;

import io.github.stockmarket.market.core.enums.MarketPhase;
import io.github.stockmarket.market.core.model.MarketContext;
import io.github.stockmarket.market.stocks.model.PriceRecord;
import io.github.stockmarket.simulation.rules.MarketRules;
import io.github.stockmarket.simulation.step.base.DailyStep;

public class DailyPriceRecordingStep extends DailyStep {

    public DailyPriceRecordingStep(MarketRules rules){
        super(rules, MarketPhase.CLOSE);
    }

    @Override
    public void apply(MarketContext context){
        context.getStocks().forEach(stock -> stock.getPriceRecords().add(new PriceRecord(stock, context.getCurrentDate())));
    }
}
