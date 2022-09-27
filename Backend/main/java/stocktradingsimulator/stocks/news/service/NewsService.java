package stocktradingsimulator.stocks.news.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stocktradingsimulator.stocks.news.entity.News;
import stocktradingsimulator.stocks.stock.model.entity.Stock;
import stocktradingsimulator.stocks.news.repository.NewsRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public List<News> findAllNews(){
        return newsRepository.findAll();
    }

    public void saveNewsForStock(Stock stock, String newsEvent, String date){
        newsRepository.save(new News(stock, newsEvent, date));
    }
}
