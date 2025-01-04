package org.api.stockmarket.stocks.news.service;

import lombok.AllArgsConstructor;
import org.api.stockmarket.stocks.news.entity.News;
import org.api.stockmarket.stocks.news.repository.NewsRepository;
import org.api.stockmarket.stocks.stock.entity.Stock;
import org.api.stockmarket.stocks.stock.exception.StockNotFoundException;
import org.api.stockmarket.stocks.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class NewsService {

    private NewsRepository newsRepository;
    private StockService stockService;

    public List<News> findAllNews() {
        return newsRepository.findAll();
    }

    public List<News> findNewsByStock(String ticker){
        if(!stockService.stockTickerExists(ticker)) throw new StockNotFoundException(ticker + " doesn't exist");
        return newsRepository.findAll().stream()
                .filter(news -> news.getStock().getTicker().equals(ticker))
                .toList();
    }

    public void saveNewsForStock(News news) {
        newsRepository.save(news);
    }

    public void saveNewsForStock(Stock stock, String newsEvent, ZonedDateTime date) {
        newsRepository.save(new News(stock, newsEvent, date));
    }
}
