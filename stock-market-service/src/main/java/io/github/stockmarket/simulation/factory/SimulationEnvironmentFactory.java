package io.github.stockmarket.simulation.factory;

import io.github.stockmarket.simulation.model.SimulationEnvironment;
import io.github.stockmarket.simulation.request.SimulationRequest;
import org.springframework.stereotype.Component;

@Component
public class SimulationEnvironmentFactory {

    public SimulationEnvironment create(SimulationRequest simulationRequest){
        return new SimulationEnvironment();
    }
}
