package stocktradingsimulator.indexfund.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stocktradingsimulator.indexfund.exception.IndexFundException;
import stocktradingsimulator.indexfund.model.*;
import stocktradingsimulator.indexfund.model.subclass.*;
import stocktradingsimulator.indexfund.helper.CalculateIndexFundPrice;
import stocktradingsimulator.indexfund.utils.Capitalize;
import stocktradingsimulator.stock.enums.MarketCap;

@RestController
@RequestMapping(value = "/api/v1/funds")
@AllArgsConstructor
public class IndexFundController {

    @Autowired
    private CalculateIndexFundPrice calculateIndexFundPrice;

    @RequestMapping(value = "/total-market")
    public IndexFund getTotalMarketFund(){
        return new TotalMarketIndexFund(calculateIndexFundPrice.findPriceOfTotalMarketFund());
    }

    @RequestMapping(value = "/cap/{marketCap}")
    public IndexFund getMarketCapFund(@PathVariable String marketCap){
        MarketCap enumMarketCap = MarketCap.valueOf(Capitalize.capitalize(marketCap));
        switch (enumMarketCap){
            case Large:
            case Mid:
            case Small:
                break;
            default :
                throw new IndexFundException();
        }
        return new MarketCapIndexFund(
                marketCap ,calculateIndexFundPrice.findPriceOfMarketCapFund(enumMarketCap));
    }

    @RequestMapping(value = "/sector/{sector}")
    public IndexFund getSectorIndexFund(@PathVariable String sector){
        return new SectorIndexFund(
                sector ,calculateIndexFundPrice.findPriceOfSectorFund(sector));
    }

    @RequestMapping(value = "/volatile")
    public IndexFund getVolatileIndexFund(){
        return new VolatilityIndexFund(
                calculateIndexFundPrice.findPriceOfVolatileFunds(true));
    }

    @RequestMapping(value = "/stable")
    public IndexFund getStableIndexFund(){
        return new StableIndexFund(
                calculateIndexFundPrice.findPriceOfVolatileFunds(false));
    }
}