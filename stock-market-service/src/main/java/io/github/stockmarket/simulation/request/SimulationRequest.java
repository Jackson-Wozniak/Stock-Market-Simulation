package io.github.stockmarket.simulation.request;

import io.github.stockmarket.simulation.rules.MarketRules;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimulationRequest {
    private MarketRulesRequest marketRules;
    private SimulationSettingsRequest simulationSettings;
}
