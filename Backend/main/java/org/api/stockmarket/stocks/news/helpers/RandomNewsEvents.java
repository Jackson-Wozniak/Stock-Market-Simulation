package org.api.stockmarket.stocks.news.helpers;

import lombok.AllArgsConstructor;
import org.api.stockmarket.stocks.news.defaults.DefaultNewsEvents;
import org.api.stockmarket.stocks.news.service.NewsService;
import org.api.stockmarket.stocks.stock.defaults.DefaultStockPrices;
import org.api.stockmarket.stocks.stock.model.entity.Stock;
import org.api.stockmarket.stocks.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RandomNewsEvents {

    @Autowired
    private final NewsService newsService;
    @Autowired
    private final StockService stockService;

    public void processPositiveNewsEvent(String date) {
        Stock stock = stockService.getRandomStock();
        stock.setPrice(stock.getPrice() * 1.1);
        stock.increaseInvestorRating();
        stockService.updateStockInDatabase(stock);

        newsService.saveNewsForStock(stock, DefaultNewsEvents.positiveNewsEvents(stock), date);
    }

    public void processNegativeNewsEvents(String date) {
        Stock stock = stockService.getRandomStock();
        stock.setPrice(stock.getPrice() * .9);
        stock.decreaseInvestorRating();
        stockService.updateStockInDatabase(stock);

        newsService.saveNewsForStock(stock, DefaultNewsEvents.negativeNewsEvents(stock), date);
    }

    //Method is called if a stock reaches below 1 and has to be reset to avoid going to zero
    public void stockBankruptNews(Stock stock, String date) {
        String eventAnnouncement = stock.getCompanyName() + " declared bankruptcy today. A buyout has " +
                "been announced, leading to a new start for the company";
        newsService.saveNewsForStock(stock, eventAnnouncement, date);

        stock.setPrice(DefaultStockPrices.getDefaultPriceWithCap(stock.getMarketCap()));
        stock.decreaseInvestorRating();
        stockService.updateStockInDatabase(stock);
    }
}
