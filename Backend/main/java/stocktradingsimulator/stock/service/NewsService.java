package stocktradingsimulator.stock.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stocktradingsimulator.stock.model.entity.News;
import stocktradingsimulator.stock.model.entity.Stock;
import stocktradingsimulator.stock.repository.NewsRepository;

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
