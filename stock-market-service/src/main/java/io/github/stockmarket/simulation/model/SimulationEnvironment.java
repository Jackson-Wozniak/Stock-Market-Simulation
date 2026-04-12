package io.github.stockmarket.simulation.model;

import io.github.stockmarket.simulation.rules.MarketRules;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimulationEnvironment {
    private MarketContext marketContext;
    private MarketRules marketRules;
    private SimulationSettings simulationSettings;
}
