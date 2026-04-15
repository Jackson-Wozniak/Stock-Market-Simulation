package io.github.stockmarket.simulation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PricingMovementRulesRequest {
    private double priceCeilingDampener;
    private double priceSignalDiminisher;
    private double priceScaleDiminisher;
}
