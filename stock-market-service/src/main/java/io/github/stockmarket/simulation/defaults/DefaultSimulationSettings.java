package io.github.stockmarket.simulation.defaults;

import io.github.stockmarket.simulation.model.SimulationSettings;

import java.time.LocalDate;

public class DefaultSimulationSettings {

    public static SimulationSettings createDefault(){
        return new SimulationSettings(1, 100, LocalDate.now());
    }
}
