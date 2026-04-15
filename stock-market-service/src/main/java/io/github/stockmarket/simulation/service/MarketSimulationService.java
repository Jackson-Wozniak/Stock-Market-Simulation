package io.github.stockmarket.simulation.service;

import io.github.stockmarket.simulation.factory.SimulationEnvironmentFactory;
import io.github.stockmarket.simulation.model.SimulationEnvironment;
import io.github.stockmarket.simulation.request.SimulationRequest;
import io.github.stockmarket.simulation.step.MarketStep;
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

        List<MarketStep> steps = List.of(
                new PricingMovementStep(environment.getMarketRules())
        );

        for(int i = 1; i <= environment.getSimulationSettings().getDays(); i++){
            for(int j = 0; j < environment.getSimulationSettings().getTicksPerDay(); j++){
                steps.forEach(m -> m.apply(environment.getMarketContext()));
            }
        }

        return environment;
    }
}
