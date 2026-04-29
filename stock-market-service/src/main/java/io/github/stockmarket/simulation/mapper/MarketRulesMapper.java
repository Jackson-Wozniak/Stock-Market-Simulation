package io.github.stockmarket.simulation.mapper;

import io.github.stockmarket.simulation.request.MarketRulesRequest;
import io.github.stockmarket.simulation.request.PricingFactorRulesRequest;
import io.github.stockmarket.simulation.request.PricingMovementRulesRequest;
import io.github.stockmarket.simulation.rules.MarketRules;
import io.github.stockmarket.simulation.rules.PricingMovementRules;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface MarketRulesMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMarketRules(MarketRulesRequest req, @MappingTarget MarketRules obj);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePricingMovementRules(PricingMovementRulesRequest req, @MappingTarget PricingMovementRules obj);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePricingFactorRules(PricingFactorRulesRequest req, @MappingTarget PricingFactorRulesRequest obj);
}
