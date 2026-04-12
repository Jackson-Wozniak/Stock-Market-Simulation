package io.github.stockmarket.simulation.service;

import io.github.stockmarket.simulation.factory.SimulationEnvironmentFactory;
import io.github.stockmarket.simulation.model.SimulationEnvironment;
import io.github.stockmarket.simulation.request.SimulationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MarketSimulationService {
    private SimulationEnvironmentFactory simulationFactory;

    public SimulationEnvironment run(SimulationRequest simulationRequest){
        SimulationEnvironment environment = simulationFactory.create(simulationRequest);

        return environment;
    }
}
