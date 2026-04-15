package io.github.stockmarket.market.core.model;

import io.github.stockmarket.market.core.enums.MarketPhase;
import io.github.stockmarket.market.stocks.model.Stock;
import io.github.stockmarket.market.core.enums.EconomicEnvironment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
public class MarketContext {
    private MarketPhase currentPhase;
    private ZonedDateTime currentDate;
    private EconomicEnvironment economicEnvironment;
    private List<Stock> stocks;
}
