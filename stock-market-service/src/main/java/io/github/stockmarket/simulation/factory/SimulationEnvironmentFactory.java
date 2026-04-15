package io.github.stockmarket.simulation.factory;

import io.github.stockmarket.market.core.enums.EconomicEnvironment;
import io.github.stockmarket.market.core.enums.MarketPhase;
import io.github.stockmarket.market.core.model.MarketContext;
import io.github.stockmarket.market.stocks.csv.StockCSVReader;
import io.github.stockmarket.market.stocks.factory.StockFactory;
import io.github.stockmarket.market.stocks.model.Stock;
import io.github.stockmarket.simulation.defaults.DefaultMarketRules;
import io.github.stockmarket.simulation.model.SimulationEnvironment;
import io.github.stockmarket.simulation.model.SimulationSettings;
import io.github.stockmarket.simulation.request.SimulationRequest;
import io.github.stockmarket.simulation.rules.MarketRules;
import io.github.stockmarket.simulation.rules.PricingFactorRules;
import io.github.stockmarket.simulation.rules.PricingMovementRules;
import io.github.stockmarket.simulation.rules.SentimentRules;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;

@Component
public class SimulationEnvironmentFactory {

    public SimulationEnvironment create(SimulationRequest request){
        MarketRules rules = new MarketRules();
        PricingMovementRules pricingMovementRules = new PricingMovementRules(0, 0, 0);
        pricingMovementRules.setPriceCeilingDampener(DefaultMarketRules.PRICE_CEILING_DAMPENER);
        pricingMovementRules.setPriceScaleDiminisher(DefaultMarketRules.PRICE_SCALE_DIMINISHER);
        pricingMovementRules.setPriceSignalDiminisher(DefaultMarketRules.PRICE_SIGNAL_DIMINISHER);

        rules.setSentimentRules(new SentimentRules());
        rules.setPricingMovementRules(pricingMovementRules);
        rules.setPricingFactorRules(new PricingFactorRules(0));

        SimulationEnvironment environment = new SimulationEnvironment();

        MarketContext context = new MarketContext();
        context.setCurrentPhase(MarketPhase.PRE_MARKET);
        context.setEconomicEnvironment(EconomicEnvironment.NEUTRAL);
        context.setCurrentDate(ZonedDateTime.now());
        List<Stock> stocks = new StockCSVReader(new StockFactory()).toEntities();
        context.setStocks(stocks);

        SimulationSettings settings = new SimulationSettings();
        settings.setDays(30);
        settings.setTicksPerDay(5);

        environment.setMarketContext(context);
        environment.setSimulationSettings(settings);
        environment.setMarketRules(rules);
        return environment;
    }
}
