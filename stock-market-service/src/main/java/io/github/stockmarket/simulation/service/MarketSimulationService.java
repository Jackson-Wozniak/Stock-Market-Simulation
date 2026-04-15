package io.github.stockmarket.simulation.service;

import io.github.stockmarket.market.core.enums.MarketPhase;
import io.github.stockmarket.market.core.model.MarketContext;
import io.github.stockmarket.simulation.factory.SimulationEnvironmentFactory;
import io.github.stockmarket.simulation.model.SimulationEnvironment;
import io.github.stockmarket.simulation.request.SimulationRequest;
import io.github.stockmarket.simulation.rules.MarketRules;
import io.github.stockmarket.simulation.step.DailyPriceRecordingStep;
import io.github.stockmarket.simulation.step.base.DailyStep;
import io.github.stockmarket.simulation.step.base.MarketStep;
import io.github.stockmarket.simulation.step.PricingMovementStep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MarketSimulationService {
    private SimulationEnvironmentFactory simulationFactory;

    public SimulationEnvironment run(SimulationRequest simulationRequest){
        SimulationEnvironment environment = simulationFactory.create(simulationRequest);
        MarketContext marketContext = environment.getMarketContext();
        MarketRules marketRules = environment.getMarketRules();

        List<MarketStep> steps = List.of(
                new PricingMovementStep(marketRules)
        );
        List<DailyStep> dailySteps = List.of(
                new DailyPriceRecordingStep(marketRules)
        );

        for(int i = 1; i <= environment.getSimulationSettings().getDays(); i++){
            marketContext.setCurrentPhase(MarketPhase.PRE_MARKET);
            dailySteps.forEach(d -> d.apply(marketContext));
            marketContext.setCurrentPhase(MarketPhase.OPEN);
            dailySteps.forEach(d -> d.apply(marketContext));

            marketContext.setCurrentPhase(MarketPhase.INTRADAY);
            dailySteps.forEach(d -> d.apply(marketContext));
            for(int j = 0; j < environment.getSimulationSettings().getTicksPerDay(); j++){
                steps.forEach(m -> m.apply(marketContext));
            }

            marketContext.setCurrentPhase(MarketPhase.CLOSE);
            dailySteps.forEach(d -> d.apply(marketContext));
            marketContext.setCurrentPhase(MarketPhase.AFTER_MARKET);
            dailySteps.forEach(d -> d.apply(marketContext));
        }

        return environment;
    }
}
