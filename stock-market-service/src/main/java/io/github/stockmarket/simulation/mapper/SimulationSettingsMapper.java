package io.github.stockmarket.simulation.mapper;

import io.github.stockmarket.simulation.model.SimulationSettings;
import io.github.stockmarket.simulation.request.MarketRulesRequest;
import io.github.stockmarket.simulation.request.SimulationSettingsRequest;
import io.github.stockmarket.simulation.rules.MarketRules;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface SimulationSettingsMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSimulationSettings(SimulationSettingsRequest req, @MappingTarget SimulationSettings obj);
}
