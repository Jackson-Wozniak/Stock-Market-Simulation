package org.api.stockmarket.indexfund.controller;

import lombok.AllArgsConstructor;
import org.api.stockmarket.indexfund.exception.IndexFundException;
import org.api.stockmarket.indexfund.helper.CalculateIndexFundPrice;
import org.api.stockmarket.indexfund.model.IndexFund;
import org.api.stockmarket.indexfund.model.subclass.*;
import org.api.stockmarket.indexfund.service.IndexFundService;
import org.api.stockmarket.indexfund.utils.Capitalize;
import org.api.stockmarket.stocks.stock.enums.MarketCap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/funds")
@AllArgsConstructor
public class IndexFundController {

    @Autowired
    private CalculateIndexFundPrice calculateIndexFundPrice;

    @Autowired
    private IndexFundService indexFundService;

    @RequestMapping(value = "/all")
    public List<IndexFund> getAllFunds() {
        return indexFundService.findAllIndexFunds();
    }

    @RequestMapping(value = "/total-market")
    public IndexFund getTotalMarketFund() {
        return new TotalMarketIndexFund(calculateIndexFundPrice.findPriceOfTotalMarketFund());
    }

    @RequestMapping(value = "/cap/{marketCap}")
    public IndexFund getMarketCapFund(@PathVariable String marketCap) {
        try {
            MarketCap enumMarketCap = MarketCap.valueOf(Capitalize.capitalize(marketCap));
            return new MarketCapIndexFund(
                    enumMarketCap, calculateIndexFundPrice.findPriceOfMarketCapFund(enumMarketCap));
        } catch (EnumConstantNotPresentException | IllegalArgumentException ex) {
            throw new IndexFundException();
        }
    }

    @RequestMapping(value = "/sector/{sector}")
    public IndexFund getSectorIndexFund(@PathVariable String sector) {
        return new SectorIndexFund(
                sector, calculateIndexFundPrice.findPriceOfSectorFund(sector));
    }

    @RequestMapping(value = "/volatile")
    public IndexFund getVolatileIndexFund() {
        return new VolatilityIndexFund(
                calculateIndexFundPrice.findPriceOfVolatileFunds(true));
    }

    @RequestMapping(value = "/stable")
    public IndexFund getStableIndexFund() {
        return new StableIndexFund(
                calculateIndexFundPrice.findPriceOfVolatileFunds(false));
    }
}