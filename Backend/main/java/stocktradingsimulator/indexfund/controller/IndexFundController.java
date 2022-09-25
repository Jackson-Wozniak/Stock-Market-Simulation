package stocktradingsimulator.indexfund.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stocktradingsimulator.indexfund.exception.IndexFundException;
import stocktradingsimulator.indexfund.model.*;
import stocktradingsimulator.indexfund.model.subclass.MarketCapIndexFund;
import stocktradingsimulator.indexfund.model.subclass.SectorIndexFund;
import stocktradingsimulator.indexfund.model.subclass.TotalMarketIndexFund;
import stocktradingsimulator.indexfund.model.subclass.VolatilityIndexFund;
import stocktradingsimulator.indexfund.helper.CalculateIndexFundPrice;

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
        switch (marketCap.toLowerCase()){
            case "large":
            case "mid":
            case "small":
                break;
            default :
                throw new IndexFundException();
        }
        return new MarketCapIndexFund(
                marketCap ,calculateIndexFundPrice.findPriceOfMarketCapFund(marketCap));
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
        return new VolatilityIndexFund(
                calculateIndexFundPrice.findPriceOfVolatileFunds(false));
    }
}