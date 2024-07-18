package org.api.stockmarket.stocks.news.helpers;

import java.time.ZonedDateTime;
import java.util.Random;

import org.api.stockmarket.stocks.news.defaults.DefaultNewsEvents;
import org.api.stockmarket.stocks.news.service.NewsService;
import org.api.stockmarket.stocks.stock.properties.DefaultStockPrices;
import org.api.stockmarket.stocks.stock.entity.Stock;
import org.api.stockmarket.stocks.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class RandomNewsEvents {

    private final NewsService newsService;
    private final StockService stockService;
    private static final Random random = new Random();

    public void newsRunner(ZonedDateTime dateTime){
        int rand = random.nextInt(30);
        if (rand == 10) processPositiveNewsEvent(dateTime);
        if (rand == 20) processNegativeNewsEvents(dateTime);
    }

    private void processPositiveNewsEvent(ZonedDateTime date) {
        Stock stock = stockService.getRandomStock();
        stock.setPrice(stock.getPrice() * 1.1);
        stock.newsEvent(true);
        stockService.updateStockInDatabase(stock);

        newsService.saveNewsForStock(stock, DefaultNewsEvents.positiveNewsEvents(stock), date);
    }

    private void processNegativeNewsEvents(ZonedDateTime date) {
        Stock stock = stockService.getRandomStock();
        stock.setPrice(stock.getPrice() * .9);
        stock.newsEvent(false);
        stockService.updateStockInDatabase(stock);

        newsService.saveNewsForStock(stock, DefaultNewsEvents.negativeNewsEvents(stock), date);
    }

    // Method is called if a stock reaches below 1 and has to be reset to avoid
    // going to zero
    public void stockBankruptNews(Stock stock, ZonedDateTime date) {
        String eventAnnouncement = stock.getCompanyName() + " declared bankruptcy today. A buyout has " +
                "been announced, leading to a new start for the company";
        newsService.saveNewsForStock(stock, eventAnnouncement, date);

        stock.setPrice(DefaultStockPrices.getDefaultPriceWithCap(stock.getMarketCap()));
        stock.newsEvent(false);
        stockService.updateStockInDatabase(stock);
    }
}
