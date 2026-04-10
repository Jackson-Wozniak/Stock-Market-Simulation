package io.github.stockmarket.simulation.model;

import io.github.stockmarket.simulation.enums.EconomicEnvironment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MacroeconomicParameters {
    private EconomicEnvironment economicEnvironment;
}
