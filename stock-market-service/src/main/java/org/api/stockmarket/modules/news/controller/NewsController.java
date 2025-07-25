package org.api.stockmarket.modules.news.controller;

import lombok.AllArgsConstructor;
import org.api.stockmarket.modules.news.entity.News;
import org.api.stockmarket.modules.news.service.NewsService;
import org.api.stockmarket.modules.stocks.exception.StockNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/news")
@AllArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @RequestMapping(value = "/{ticker}")
    public List<News> findStockNewsWithTicker(@PathVariable String ticker) throws StockNotFoundException {
        return newsService.findNewsByStock(ticker);
    }

    @GetMapping
    public List<News> findAllMarketNews() {
        return newsService.findAllNews();
    }
}
