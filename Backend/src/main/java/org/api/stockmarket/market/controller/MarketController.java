package org.api.stockmarket.market.controller;

import lombok.AllArgsConstructor;
import org.api.stockmarket.market.entity.Market;
import org.api.stockmarket.market.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/market")
@AllArgsConstructor
public class MarketController {

    @Autowired
    private final MarketService marketService;

    @RequestMapping()
    public Market findMarketEntity() {
        return marketService.findMarketEntity();
    }
}
