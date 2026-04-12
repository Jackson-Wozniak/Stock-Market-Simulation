package io.github.stockmarket.simulation.model;

import io.github.stockmarket.simulation.enums.EconomicEnvironment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MarketContext {
    private LocalDateTime currentDate;
    private EconomicEnvironment economicEnvironment;
}
