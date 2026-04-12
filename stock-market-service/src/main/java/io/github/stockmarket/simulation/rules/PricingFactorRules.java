package io.github.stockmarket.simulation.rules;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PricingFactorRules {
    //the range a PricingAttributes factor can be. Example: if 50 then the factor value is [-50, 50]
    private double absoluteValueRange;
}
