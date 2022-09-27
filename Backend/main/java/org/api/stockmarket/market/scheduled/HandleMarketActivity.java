package org.api.stockmarket.market.scheduled;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.api.stockmarket.market.entity.Market;
import org.api.stockmarket.market.service.MarketService;
import org.api.stockmarket.market.utils.GetRandomNumber;
import org.api.stockmarket.market.utils.MarketTrajectoryUtils;
import org.api.stockmarket.stocks.earnings.helpers.ReleaseEarningsReport;
import org.api.stockmarket.stocks.news.helpers.RandomNewsEvents;
import org.api.stockmarket.stocks.stock.model.entity.Stock;
import org.api.stockmarket.stocks.stock.service.StockService;

import java.util.List;

@Component
@AllArgsConstructor
public class HandleMarketActivity {

    @Autowired
    private final StockService stockService;
    @Autowired
    private final ChangeStockPrices changeStockPrices;
    @Autowired
    private final MarketService marketService;
    @Autowired
    private final RandomNewsEvents randomNewsEvents;
    @Autowired
    private final ReleaseEarningsReport releaseEarningsReport;

    public String dailyMarketActivity(){
        updateNewStockInformation(true);
        createRandomNewsEvents();
        String marketDate = incrementMarketDay();
        if(timeForQuarterlyEarnings(marketDate)){
            releaseEarningsReport.handleQuarterlyEarningsReports(
                    stockService.getAllStocks(), marketDate);
        }
        return marketDate;
    }

    public void updateNewStockInformation(boolean endOfDay){
        List<Stock> stocks = stockService.getAllStocks();
        stocks.forEach(stock -> {
            stock.setPrice(changeStockPrices.automaticPriceChange(stock));
            if(endOfDay){
                //avoid stocks going to zero with bankruptcy event
                if(stock.getPrice() < 1){
                    randomNewsEvents.stockBankruptNews(stock, marketService.findMarketEntity().getDate());
                    return;
                }
                stock.updateMomentumStreak();
                stock.updateMomentum();
                stock.setLastDayPrice(stock.getPrice());
            }
            stockService.updateStockInDatabase(stock);
        });
    }

    private String incrementMarketDay(){
        Market market = marketService.findMarketEntity();
        market.incrementDay();
        marketService.saveMarketEntity(market);
        return market.getDate();
    }

    public void updateMarketMonthlyValues(){
        Market market = marketService.findMarketEntity();
        market.setMarketTrajectory(MarketTrajectoryUtils.getNewMarketTrajectory(
                market, stockService.getAllStocks()));
        market.setLastMonthAveragePrice(MarketTrajectoryUtils.stockPricesAverage(
                stockService.getAllStocks()));
        marketService.saveMarketEntity(market);
    }

    private void createRandomNewsEvents(){
        int randomNumber = GetRandomNumber.drawRandomNumberToThirty();
        if(randomNumber == 10){
            randomNewsEvents.processPositiveNewsEvent(marketService.findMarketEntity().getDate());
            System.out.println("Positive News");
        }else if(randomNumber == 20){
            randomNewsEvents.processNegativeNewsEvents(marketService.findMarketEntity().getDate());
            System.out.println("Negative News");
        }
    }

    private boolean timeForQuarterlyEarnings(String marketDate){
       String[] dateArray = marketDate.split("/");
       //earnings report released on first day of 3rd, 6th, 9th and 12th month
       return Integer.parseInt(dateArray[0]) % 3 == 0 && Integer.parseInt(dateArray[1]) == 1;
    }
}
