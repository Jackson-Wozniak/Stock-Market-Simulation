package stocktradingsimulator.stocks.earnings;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stocktradingsimulator.market.service.MarketService;
import stocktradingsimulator.stocks.stock.model.entity.Stock;
import stocktradingsimulator.stocks.stock.service.StockService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/earnings")
@AllArgsConstructor
public class EarningsController {

    @Autowired
    private final StockService stockService;
    @Autowired
    private final MarketService marketService;
    @Autowired
    private final ReleaseEarningsReport releaseEarningsReport;
    @Autowired
    private final EarningsService earningsService;

    @RequestMapping
    public List<EarningsReport> random(){
        releaseEarningsReport.handleQuarterlyEarningsReports(
                stockService.getAllStocks(), marketService.findMarketEntity().getDate());
        return earningsService.findAllEarningsReports();
    }
}
