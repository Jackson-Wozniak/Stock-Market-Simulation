package org.api.stockmarket.modules.news.engines;

import lombok.AllArgsConstructor;
import org.api.stockmarket.modules.news.entity.News;
import org.api.stockmarket.modules.news.factory.NewsFactory;
import org.api.stockmarket.modules.news.model.NewsStory;
import org.api.stockmarket.modules.news.service.NewsService;
import org.api.stockmarket.modules.stocks.entity.Stock;
import org.api.stockmarket.modules.stocks.service.StockService;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Random;

/*
Class that manages the creation and release of news stories
Begin by releasing news stories at a certain % chance, and then generate/save new stories
 */
@Service
@AllArgsConstructor
public class NewsEngine {

//    private final NewsService newsService;
//    private final NewsFactory newsFactory;
//    private final StockService stockService;
//    private static final Random random = new Random();
//
//    public void runDailyNewsStories(List<Stock> stocks, ZonedDateTime date){
//        stocks.forEach(stock -> {
//            if(stock.getPrice() < 1){
//                stockBankruptNews(stock, date);
//                return;
//            }
//
//            //~2% chance of news story being released daily
//            //positive/negative depends on the random value and the stocks momentum
//            int rand = random.nextInt(100);
//
//            switch(rand){
//                case 10 : {
//                    processNews(stock, newsFactory.generate(true), date);
//                    break;
//                }
//                case 20 : {
//                    processNews(stock, newsFactory.generate(false), date);
//                    break;
//                }
//                case 25 : {
//                    if(stock.getMomentum() == -1){
//                        processNews(stock, newsFactory.generate(false), date);
//                    }else if (stock.getMomentum() == 1){
//                        processNews(stock, newsFactory.generate(true), date);
//                    }
//                    break;
//                }
//            }
//        });
//    }
//
//    private void processNews(Stock stock, NewsStory news, ZonedDateTime date) {
//        stock.multiplyPrice(news.getPercentChange());
//        stock.newsEvent(true);
//        stockService.updateStockInDatabase(stock);
//
//        newsService.saveNewsForStock(new News(stock, news.getStory(stock), date));
//    }
//
//    // Method is called if a stock reaches below 1 and has to be reset to avoid
//    // going to zero
//    private void stockBankruptNews(Stock stock, ZonedDateTime date) {
//        String eventAnnouncement = stock.getCompanyName() + " declared bankruptcy today. A buyout has " +
//                "been announced, leading to a new start for the company";
//        newsService.saveNewsForStock(stock, eventAnnouncement, date);
//
//        stock.setPrice(DefaultStockPrices.getDefaultPriceWithCap(stock.getMarketCap()));
//        stock.newsEvent(false);
//        stockService.updateStockInDatabase(stock);
//    }
}
