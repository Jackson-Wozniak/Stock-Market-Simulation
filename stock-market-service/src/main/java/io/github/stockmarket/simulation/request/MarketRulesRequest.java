package io.github.stockmarket.simulation.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarketRulesRequest {
    private PricingMovementRulesRequest pricingMovementRules;
}
