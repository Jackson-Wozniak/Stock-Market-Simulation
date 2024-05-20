package org.api.stockmarket.stocks.news.service;

import lombok.AllArgsConstructor;
import org.api.stockmarket.stocks.news.entity.News;
import org.api.stockmarket.stocks.news.repository.NewsRepository;
import org.api.stockmarket.stocks.stock.entity.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public List<News> findAllNews() {
        return newsRepository.findAll();
    }

    public void saveNewsForStock(Stock stock, String newsEvent, ZonedDateTime date) {
        newsRepository.save(new News(stock, newsEvent, date));
    }
}
