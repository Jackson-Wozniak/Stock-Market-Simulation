package io.github.stockmarket.simulation.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarketParameters {
    private PricingParameters pricing;
    private MacroeconomicParameters macroeconomic;
}
