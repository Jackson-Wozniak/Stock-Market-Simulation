package stocktradingsimulator.stocks.earnings;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stocktradingsimulator.market.exception.DateFormatException;
import stocktradingsimulator.market.service.MarketService;
import stocktradingsimulator.market.utils.DateConversion;
import stocktradingsimulator.stocks.stock.model.entity.Stock;
import stocktradingsimulator.stocks.stock.service.StockService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/earnings")
@AllArgsConstructor
public class EarningsController {

    @Autowired
    private final EarningsService earningsService;
    @Autowired
    private final StockService stockService;

    @RequestMapping(value = "/all")
    public List<EarningsReport> getAllEarningsReportHistory(){
        return earningsService.findAllEarningsReports();
    }

    @RequestMapping(value = "/stock/{ticker}")
    public List<EarningsReport> getAllEarningsHistoryFromStock(@PathVariable String ticker){
        return stockService.getStockByTickerSymbol(ticker).getEarningsHistory();
    }

    //date is formatted as month_day_year here instead of month/day/year
    @RequestMapping(value = "/date/{date}")
    public List<EarningsReport> getEarningsOnDate(@PathVariable String date){
        date = date.replace("_", "/");
        if(!DateConversion.dateIsFormattedCorrectly(date)){
            throw new DateFormatException();
        }
        return earningsService.findAllEarningsByDate(date);
    }

}
