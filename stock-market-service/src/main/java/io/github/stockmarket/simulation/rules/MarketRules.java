package io.github.stockmarket.simulation.rules;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarketRules {
    private PricingRules pricingRules;
    private FactorRules factorRules;
}
