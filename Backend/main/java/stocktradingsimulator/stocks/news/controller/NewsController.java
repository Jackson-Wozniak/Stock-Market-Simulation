package stocktradingsimulator.stocks.news.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stocktradingsimulator.stocks.news.entity.News;
import stocktradingsimulator.stocks.news.service.NewsService;
import stocktradingsimulator.stocks.stock.exception.StockNotFoundException;
import stocktradingsimulator.stocks.stock.model.payload.StockDto;
import stocktradingsimulator.stocks.stock.service.StockService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/news")
@AllArgsConstructor
public class NewsController {

    @Autowired
    private final StockService stockService;
    @Autowired
    private final NewsService newsService;

    @RequestMapping(value = "/{ticker}")
    public StockDto findStockNewsWithTicker(@PathVariable String ticker) throws StockNotFoundException {
        return new StockDto(stockService.getStockByTickerSymbol(ticker));
    }

    @RequestMapping(value = "/all")
    public List<News> findAllMarketNews(){
        return newsService.findAllNews();
    }
}
