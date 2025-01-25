package org.api.stockmarket.indexfund.controller;

import lombok.AllArgsConstructor;
import org.api.stockmarket.indexfund.dtos.IndexFundDto;
import org.api.stockmarket.indexfund.exception.IndexFundException;
import org.api.stockmarket.indexfund.model.IndexFund;
import org.api.stockmarket.indexfund.service.IndexFundService;
import org.api.stockmarket.stocks.stock.enums.MarketCap;
import org.api.stockmarket.stocks.stock.enums.Volatility;
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

    @RequestMapping(value = "/total-market")
    public IndexFundDto getTotalMarketFund() {
        return indexFundService.findTotalMarketFund().toDto();
    }

    @RequestMapping(value = "/cap")
    public List<IndexFundDto> getAllMarketCapFunds() {
        return indexFundService.findMarketCapFunds().stream().map(IndexFund::toDto).toList();
    }

    @RequestMapping(value = "/cap/{cap}")
    public IndexFundDto getMarketCapFund(@PathVariable String cap) {
        if(MarketCap.map(cap) == null) throw IndexFundException.invalidMarketCap(cap);
        return indexFundService.findMarketCapFunds(MarketCap.map(cap)).toDto();
    }

    @RequestMapping(value = "/sector")
    public List<IndexFundDto> getAllSectorIndexFunds() {
        return indexFundService.findSectorFunds().stream().map(IndexFund::toDto).toList();
    }

    @RequestMapping(value = "/sector/{sector}")
    public IndexFundDto getSectorIndexFund(@PathVariable String sector) {
        return indexFundService.findSectorFunds(sector).toDto();
    }

    @RequestMapping(value = "/volatility")
    public List<IndexFundDto> getAllVolatilityFunds() {
        return indexFundService.findVolatilityFunds().stream().map(IndexFund::toDto).toList();
    }

    @RequestMapping(value = "/volatility/{volatility}")
    public IndexFundDto getVolatileIndexFund(@PathVariable String volatility) {
        if(Volatility.map(volatility) == null) throw IndexFundException.invalidVolatility(volatility);
        return indexFundService.findVolatilityFunds(Volatility.map(volatility)).toDto();
    }
}