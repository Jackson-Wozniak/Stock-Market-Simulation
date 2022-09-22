package stocktradingsimulator.stock.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stocktradingsimulator.stock.exception.StockNotFoundException;
import stocktradingsimulator.stock.model.entity.News;
import stocktradingsimulator.stock.model.payload.StockDto;
import stocktradingsimulator.stock.service.NewsService;
import stocktradingsimulator.stock.service.StockService;

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
