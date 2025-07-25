package org.api.stockmarket.market.controller;

import lombok.AllArgsConstructor;
import org.api.stockmarket.market.payload.MarketDTO;
import org.api.stockmarket.market.service.MarketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/market")
@AllArgsConstructor
public class MarketController {

    private final MarketService marketService;

    @GetMapping()
    public ResponseEntity<MarketDTO> getMarketEntity() {
        return ResponseEntity.ok(new MarketDTO(marketService.findMarketEntity()));
    }
}
