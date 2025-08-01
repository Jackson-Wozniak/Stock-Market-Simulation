package org.api.stockmarket.modules.indexfunds.controller;

import lombok.AllArgsConstructor;
import org.api.stockmarket.modules.indexfunds.dtos.IndexFundDto;
import org.api.stockmarket.modules.indexfunds.exception.IndexFundException;
import org.api.stockmarket.modules.indexfunds.model.IndexFund;
import org.api.stockmarket.modules.indexfunds.service.IndexFundService;
import org.api.stockmarket.modules.stocks.enums.MarketCap;
import org.api.stockmarket.modules.stocks.enums.Volatility;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/funds")
@AllArgsConstructor
public class IndexFundController {

    private IndexFundService indexFundService;

    @GetMapping
    public List<IndexFundDto> getAllFunds() {
        return indexFundService.findAllIndexFunds().stream().map(IndexFund::toDto).toList();
    }

    @GetMapping(value = "/total-market")
    public IndexFundDto getTotalMarketFund() {
        return indexFundService.findTotalMarketFund().toDto();
    }

    @GetMapping(value = "/cap")
    public List<IndexFundDto> getAllMarketCapFunds() {
        return indexFundService.findMarketCapFunds().stream().map(IndexFund::toDto).toList();
    }

    @GetMapping(value = "/cap/{cap}")
    public IndexFundDto getMarketCapFund(@PathVariable String cap) {
        if(MarketCap.map(cap) == null) throw IndexFundException.invalidMarketCap(cap);
        return indexFundService.findMarketCapFunds(MarketCap.map(cap)).toDto();
    }

    @GetMapping(value = "/sector")
    public List<IndexFundDto> getAllSectorIndexFunds() {
        return indexFundService.findSectorFunds().stream().map(IndexFund::toDto).toList();
    }

    @GetMapping(value = "/sector/{sector}")
    public IndexFundDto getSectorIndexFund(@PathVariable String sector) {
        return indexFundService.findSectorFunds(sector).toDto();
    }

    @GetMapping(value = "/volatility")
    public List<IndexFundDto> getAllVolatilityFunds() {
        return indexFundService.findVolatilityFunds().stream().map(IndexFund::toDto).toList();
    }

    @GetMapping(value = "/volatility/{volatility}")
    public IndexFundDto getVolatileIndexFund(@PathVariable String volatility) {
        if(Volatility.map(volatility) == null) throw IndexFundException.invalidVolatility(volatility);
        return indexFundService.findVolatilityFunds(Volatility.map(volatility)).toDto();
    }
}